package com.hulkdx.findprofessional.tests.token

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.hulkdx.findprofessional.MainActivity
import com.hulkdx.findprofessional.common.config.api.interceptor.AppInterceptor
import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.feature.authentication.model.Auth
import com.hulkdx.findprofessional.common.feature.authentication.login.LoginApi
import com.hulkdx.findprofessional.common.feature.authentication.login.RefreshTokenApi
import com.hulkdx.findprofessional.common.feature.authentication.model.Token
import com.hulkdx.findprofessional.common.feature.authentication.model.User
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.LoginRequest
import com.hulkdx.findprofessional.tests.screen.login.launchLoginScreen
import com.hulkdx.findprofessional.utils.UiTestRule
import com.hulkdx.findprofessional.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.utils.get
import com.hulkdx.findprofessional.utils.getAll
import com.hulkdx.findprofessional.utils.newUser
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.bind
import org.koin.dsl.module

class RefreshTokenTest {

    companion object {
        private val INVALID_TOKENS = Auth(Token("invalid_irrelevant_at", "invalid_irrelevant_rt"), newUser())
        private val VALID_TOKENS = Auth(Token("valid_irrelevant_at", "valid_irrelevant_rt"), newUser())
    }

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 1)
    val rule = UiTestRule(composeRule)

    private lateinit var randomApi: RandomTestRefreshTokenApi
    private val accessTokenStorage: AccessTokenStorage = get()
    private val loginApi = LoginApiMock()
    private val refreshApi = RefreshApiMock()

    private val module = module {
        single { refreshApi } bind RefreshTokenApi::class
        single { loginApi } bind LoginApi::class
    }

    @Before
    fun setUp() {
        loadKoinModules(module)
        randomApi = RandomTestRefreshTokenApi(get(), getAll(), accessTokenStorage)
    }

    @After
    fun tearDown() {
        unloadKoinModules(module)
    }

    @Test
    fun when_invalidAccessToken_then_intercept_should_call_refreshToken() {
        // Arrange
        refreshApiResponseValidToken()
        loginWithValidTokens()
        // Act
        runBlocking {
            callApiWithInvalidToken()
        }
        // Assert
        assertThat(refreshApi.isRefreshTokenCalled, `is`(true))
    }

    @Test
    fun when_invalidAccessToken_and_refreshApi_response_invalidTokens_then_intercept_should_logout()  {
        // Arrange
        refreshApiResponseUnauthorized()
        loginWithValidTokens()
        // Act
        runBlocking {
            callApiWithInvalidToken()
        }
        // Asserts
        composeRule.assertNodeIsDisplayed("LoginScreen")
    }

    // region helper methods

    private fun refreshApiResponseValidToken() {
        refreshApi.response = VALID_TOKENS
    }

    private fun refreshApiResponseUnauthorized() {
        refreshApi.responseError = {
            throwUnauthorizedException()
        }
    }

    private fun loginWithValidTokens() {
        loginApi.response = VALID_TOKENS
        launchLoginScreen(composeRule) {
            typeEmail("irrelevant")
            typePassword("irrelevant")
            pressSignInButton()
        }.verify {
            homeScreenShown()
        }
    }

    private suspend fun callApiWithInvalidToken() {
        accessTokenStorage.set(INVALID_TOKENS.token.accessToken)
        runCatching {
            randomApi.randomApi()
        }
    }

    private suspend fun throwUnauthorizedException() {
        val config: HttpClientConfig<*>.() -> Unit = get()
        val mockEngine = MockEngine { _ ->
            respond(
                content = ByteReadChannel(""),
                status = HttpStatusCode.Unauthorized,
                headers = headersOf()
            )
        }
        val client = HttpClient(mockEngine, config)
        client.get("")
    }

    // endregion
    // region mock classes

    private class LoginApiMock : LoginApi {
        var response: Auth? = null

        override suspend fun login(request: LoginRequest): Auth {
            return response!!
        }
    }

    private class RefreshApiMock : RefreshTokenApi {
        var response: Auth? = null
        var responseError: (suspend () -> Unit)? = null
        var isRefreshTokenCalled = false

        override suspend fun refreshToken(refreshToken: String, accessToken: String): Auth {
            isRefreshTokenCalled = true
            responseError?.let { it() }
            return response!!
        }
    }

    private class RandomTestRefreshTokenApi(
        private val config: HttpClientConfig<*>.() -> Unit,
        private val interceptors: List<AppInterceptor>,
        private val accessTokenStorage: AccessTokenStorage,
    ) {
        suspend fun randomApi(): String {
            return httpClient().post {
                url("irrelevant")
                header(HttpHeaders.Authorization, "Bearer ${accessTokenStorage.get()}")
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
            return getAccessToken() != INVALID_TOKENS.token.accessToken
        }
    }

    // endregion
}
