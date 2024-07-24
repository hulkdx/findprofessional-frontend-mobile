@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.token

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.config.api.AppApiProvider
import com.hulkdx.findprofessional.app.test.runAppUiTest
import com.hulkdx.findprofessional.app.test.screen.login.launchLoginScreen
import com.hulkdx.findprofessional.app.test.utils.Rule
import com.hulkdx.findprofessional.app.test.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.app.test.utils.get
import com.hulkdx.findprofessional.app.test.utils.getAll
import com.hulkdx.findprofessional.core.api.ApiInterceptor
import com.hulkdx.findprofessional.core.api.getAccessToken
import com.hulkdx.findprofessional.core.model.user.Token
import com.hulkdx.findprofessional.core.model.user.UserData
import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.feature.authentication.login.api.LoginApi
import com.hulkdx.findprofessional.feature.authentication.login.api.RefreshTokenApi
import com.hulkdx.findprofessional.feature.authentication.login.model.LoginRequest
import com.hulkdx.findprofessional.libs.common.tests.newUser
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.test.Test
import kotlin.test.assertEquals

class RefreshTokenTest {

    companion object {
        private val INVALID_TOKENS = Token("invalid_irrelevant_at", "invalid_irrelevant_rt")
        private val VALID_TOKENS = Token("valid_irrelevant_at", "valid_irrelevant_rt")
    }

    private val module = module {
        single { refreshApi } bind RefreshTokenApi::class
        single { loginApi } bind LoginApi::class
    }

    private lateinit var randomApi: RandomTestRefreshTokenApi
    private lateinit var userStorage: UserStorage
    private val loginApi = LoginApiMock()
    private val refreshApi = RefreshApiMock()

    @Test
    fun when_invalidAccessToken_then_intercept_should_call_refreshToken() = runAppUiTest(
        before = {
            loadKoinModules(module)
            userStorage = get()
            randomApi = RandomTestRefreshTokenApi(getHttpClientConfig(), getAll(), userStorage)
        },
        test = {
            // Arrange
            refreshApiResponseValidToken()
            loginWithValidTokens(this)
            // Act
            runBlocking {
                callApiWithInvalidToken()
            }
            // Assert
            assertEquals(refreshApi.isRefreshTokenCalled, true)
        },
        after = {
            unloadKoinModules(module)
        }
    )

    @Test
    fun when_invalidAccessToken_and_refreshApi_response_invalidTokens_then_intercept_should_logout() =
        runAppUiTest(
            before = {
                loadKoinModules(module)
                userStorage = get()
                randomApi = RandomTestRefreshTokenApi(getHttpClientConfig(), getAll(), userStorage)
            },
            test = {
                // Arrange
                refreshApiResponseUnauthorized()
                loginWithValidTokens(this)
                // Act
                runBlocking {
                    callApiWithInvalidToken()
                }
                // Asserts
                assertNodeIsDisplayed("LoginScreen")
            },
            after = {
                unloadKoinModules(module)
            }
        )

    // region helper methods

    private fun refreshApiResponseValidToken() {
        refreshApi.response = VALID_TOKENS
    }

    private fun refreshApiResponseUnauthorized() {
        refreshApi.responseError = {
            throwUnauthorizedException()
        }
    }

    private fun loginWithValidTokens(composeRule: Rule) {
        loginApi.response = UserData(VALID_TOKENS, newUser())
        launchLoginScreen(composeRule) {
            typeEmail("irrelevant")
            typePassword("irrelevant")
            pressSignInButton()
        }.verify {
            homeScreenShown()
        }
    }

    private suspend fun callApiWithInvalidToken() {
        userStorage.set(UserData(INVALID_TOKENS, newUser()))
        runCatching {
            randomApi.randomApi()
        }
    }

    private suspend fun throwUnauthorizedException() {
        val mockEngine = MockEngine { _ ->
            respond(
                content = ByteReadChannel(""),
                status = HttpStatusCode.Unauthorized,
                headers = headersOf()
            )
        }
        val client = HttpClient(mockEngine, getHttpClientConfig())
        client.get("")
    }

    private fun getHttpClientConfig() = AppApiProvider.getConfig(get())

    // endregion
    // region mock classes

    private class LoginApiMock : LoginApi {
        var response: UserData? = null

        override suspend fun login(request: LoginRequest): UserData {
            return response!!
        }
    }

    private class RefreshApiMock : RefreshTokenApi {
        var response: Token? = null
        var responseError: (suspend () -> Unit)? = null
        var isRefreshTokenCalled = false

        override suspend fun refreshToken(refreshToken: String, accessToken: String): Token {
            isRefreshTokenCalled = true
            responseError?.let { it() }
            return response!!
        }
    }

    private class RandomTestRefreshTokenApi(
        private val config: HttpClientConfig<*>.() -> Unit,
        private val interceptors: List<ApiInterceptor>,
        private val userStorage: UserStorage,
    ) {
        suspend fun randomApi(): String {
            return httpClient().post {
                url("irrelevant")
                header(HttpHeaders.Authorization, "Bearer ${userStorage.getAccessToken()}")
            }.body()
        }

        private fun httpClient(): HttpClient {
            val mockEngine = MockEngine { request ->
                respond(
                    content = ByteReadChannel(""),
                    status = if (request.hasValidAccessToken()) HttpStatusCode.OK else HttpStatusCode.Unauthorized,
                    headers = headersOf()
                )
            }
            val client = HttpClient(mockEngine, config).apply {
                plugin(HttpSend).apply {
                    for (interceptor in interceptors) {
                        intercept(interceptor::intercept)
                    }
                }
            }
            return client
        }

        private fun HttpRequestData.getAccessToken(): String {
            return headers[HttpHeaders.Authorization]?.split("Bearer ")?.getOrNull(1) ?: ""
        }

        private fun HttpRequestData.hasValidAccessToken(): Boolean {
            return getAccessToken() != INVALID_TOKENS.accessToken
        }
    }

    // endregion
}
