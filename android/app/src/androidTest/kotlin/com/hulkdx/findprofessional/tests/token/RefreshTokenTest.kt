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
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import com.hulkdx.findprofessional.tests.screen.login.launchLoginScreen
import com.hulkdx.findprofessional.utils.KoinTestRule
import com.hulkdx.findprofessional.utils.ScreenshotOnFailureRule
import com.hulkdx.findprofessional.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.utils.get
import com.hulkdx.findprofessional.utils.getAll
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
import org.koin.dsl.bind
import org.koin.dsl.module

class RefreshTokenTest {

    companion object {
        private val INVALID_TOKENS = Auth(Token("invalid_irrelevant_at", "invalid_irrelevant_rt"), User("email"))
        private val VALID_TOKENS = Auth(Token("valid_irrelevant_at", "valid_irrelevant_rt"), User("email"))
    }

    private val module = module {
        single { refreshApi } bind RefreshTokenApi::class
        single { loginApi } bind LoginApi::class
    }

    @get:Rule(order = 1)
    val koinTestRule = KoinTestRule(module)

    @get:Rule(order = 2)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 3)
    val screenshotOnFailureRule = ScreenshotOnFailureRule(composeRule)

    private lateinit var randomApi: RandomTestRefreshTokenApi
    private lateinit var accessTokenStorage: AccessTokenStorage
    private val loginApi = LoginApiMock()
    private val refreshApi = RefreshApiMock()

    @Before
    fun setUp() {
        accessTokenStorage = get()
        randomApi = RandomTestRefreshTokenApi(get(), getAll(), accessTokenStorage)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun when_invalidAccessToken_then_intercept_should_call_refreshToken() = runBlocking {
        // Arrange
        refreshApiResponseValidToken()
        loginWithValidTokens()
        // Act
        callApiWithInvalidToken()
        // Assert
        assertThat(refreshApi.isRefreshTokenCalled, `is`(true))
    }

    @Test
    fun when_invalidAccessToken_and_refreshApi_response_invalidTokens_then_intercept_should_logout() = runBlocking {
        // Arrange
        refreshApiResponseUnauthorized()
        loginWithValidTokens()
        // Act
        callApiWithInvalidToken()
        // Asserts
        composeRule.assertNodeIsDisplayed("LoginScreen")
    }

    // region helper methods

    private fun refreshApiResponseValidToken() {
        refreshApi.response = VALID_TOKENS
    }

    private suspend fun refreshApiResponseUnauthorized() {
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

        override suspend fun login(request: AuthRequest): Auth {
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
