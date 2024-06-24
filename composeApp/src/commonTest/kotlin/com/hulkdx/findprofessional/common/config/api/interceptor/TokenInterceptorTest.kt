@file:OptIn(InternalAPI::class, ExperimentalCoroutinesApi::class)

package com.hulkdx.findprofessional.common.config.api.interceptor

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.config.storage.RefreshTokenStorage
import com.hulkdx.findprofessional.common.feature.authentication.model.Auth
import com.hulkdx.findprofessional.common.feature.authentication.login.RefreshTokenApi
import com.hulkdx.findprofessional.common.feature.authentication.logout.LogoutUseCase
import com.hulkdx.findprofessional.common.feature.authentication.model.Token
import com.hulkdx.findprofessional.common.feature.authentication.model.User
import com.hulkdx.findprofessional.common.utils.KoinTestUtil
import com.hulkdx.findprofessional.common.utils.newUser
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.util.date.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.coroutines.coroutineContext
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class TokenInterceptorTest {

    private lateinit var sut: TokenInterceptor

    private val refreshTokenApi = RefreshTokenApiMock()
    private val accessTokenStorage = AccessTokenStorageMock()
    private val refreshTokenStorage = RefreshTokenStorageMock()

    @BeforeTest
    fun setUp() {
        KoinTestUtil.startKoin()
        sut = TokenInterceptor(
            refreshTokenApi,
            accessTokenStorage,
            refreshTokenStorage,
            DummyLogoutUseCase()
        )
    }

    @AfterTest
    fun tearDown() {
        KoinTestUtil.stopKoin()
    }

    @Test
    fun `intercept unauthorized when accessToken and refreshToken is not empty then should store and refresh token and retry call`() = runTest {
        // Arrange
        accessTokenStorage.value = "not empty"
        refreshTokenStorage.value = "not empty"
        val sender = SenderMock().apply {
            response(
                request = { header(HttpHeaders.Authorization, "not empty") },
                responseStatusCode = HttpStatusCode.Unauthorized,
            )
        }
        val request = request {}
        // Act
        sut.intercept(sender, request)
        // Assert
        assertEquals(true, refreshTokenApi.isRefreshTokenCalled)
        assertEquals(true, accessTokenStorage.isSetCalled)
        assertEquals(true, refreshTokenStorage.isSetCalled)
        val isRetriedCall = (sender.isExecuteCalledCount == 2)
        assertEquals(true, isRetriedCall)
    }

    @Test
    fun `don't intercept filtered url`() = runTest {
        // Arrange
        val filterUrl = "/auth/refresh"

        accessTokenStorage.value = "not empty"
        refreshTokenStorage.value = "not empty"
        val sender = SenderMock().apply {
            response(
                request = { url(filterUrl) },
                responseStatusCode = HttpStatusCode.Unauthorized
            )
        }
        val request = request {}
        // Act
        sut.intercept(sender, request)
        // Assert
        assertEquals(false, refreshTokenApi.isRefreshTokenCalled)
        assertEquals(false, accessTokenStorage.isSetCalled)
        assertEquals(false, refreshTokenStorage.isSetCalled)
        val isRetriedCall = (sender.isExecuteCalledCount == 2)
        assertEquals(false, isRetriedCall)
    }

    @Test
    fun `don't intercept for request without authorization header`() = runTest {
        // Arrange
        val request: HttpRequestBuilder.() -> Unit = {
            // empty header
            headersOf()
        }
        accessTokenStorage.value = "not empty"
        refreshTokenStorage.value = "not empty"
        val sender = SenderMock().apply {
            response(
                request = request,
                responseStatusCode = HttpStatusCode.Unauthorized,
            )
        }
        // Act
        sut.intercept(sender, request {})
        // Assert
        assertEquals(false, refreshTokenApi.isRefreshTokenCalled)
        assertEquals(false, accessTokenStorage.isSetCalled)
        assertEquals(false, refreshTokenStorage.isSetCalled)
        val isRetriedCall = (sender.isExecuteCalledCount == 2)
        assertEquals(false, isRetriedCall)
    }


    @Test
    fun `intercept should remove the old access token and replace it with new one`() = runTest {
        // Arrange
        val oldAccessToken = "oldAccessToken"
        val newAccessToken = "newAccessToken"
        val req: HttpRequestBuilder.() -> Unit = {
            header(HttpHeaders.Authorization, "Bearer $oldAccessToken")
        }
        refreshTokenApi.response = Token(newAccessToken, "irrelevant")

        accessTokenStorage.value = "not empty"
        refreshTokenStorage.value = "not empty"
        val sender = SenderMock().apply {
            response(
                request = req,
                responseStatusCode = HttpStatusCode.Unauthorized,
            )
        }
        // Act
        sut.intercept(sender, request(req))
        // Assert
        val responseHeader = sender.requestBuilder?.headers?.get(HttpHeaders.Authorization)
        assertEquals("Bearer $newAccessToken", responseHeader)
    }

    // region mock classes

    private class AccessTokenStorageMock : AccessTokenStorage {
        var value: String? = null
        var isSetCalled = false

        override suspend fun get(): String? {
            return value
        }

        override suspend fun set(value: String) {
            isSetCalled = true
            this.value = value
        }

        override suspend fun remove() {}
    }

    private class RefreshTokenStorageMock : RefreshTokenStorage {
        var value: String? = null
        var isSetCalled = false

        override suspend fun get(): String? {
            return value
        }

        override suspend fun set(value: String) {
            isSetCalled = true
            this.value = value
        }

        override suspend fun remove() {}
    }

    private class RefreshTokenApiMock : RefreshTokenApi {
        var isRefreshTokenCalled = false
        var response = Token("access", "refresh")

        override suspend fun refreshToken(refreshToken: String, accessToken: String): Token {
            isRefreshTokenCalled = true
            return response
        }
    }

    private class SenderMock : Sender {
        private var response: HttpClientCall? = null
        var isExecuteCalledCount = 0
        var requestBuilder: HttpRequestBuilder? = null

        override suspend fun execute(requestBuilder: HttpRequestBuilder): HttpClientCall {
            isExecuteCalledCount += 1
            this.requestBuilder = requestBuilder
            return response!!
        }

        suspend fun response(
            request: HttpRequestBuilder.() -> Unit = {},
            responseStatusCode: HttpStatusCode = HttpStatusCode.OK,
            responseBody: Any = "",
            requestTime: GMTDate = GMTDate.START,
            headers: Headers = headersOf(),
            version: HttpProtocolVersion = HttpProtocolVersion.HTTP_1_1,
        ) {
            response = HttpClientCall(
                HttpClient {},
                request(request).build(),
                HttpResponseData(
                    responseStatusCode,
                    requestTime,
                    headers,
                    version,
                    responseBody,
                    coroutineContext,
                )
            )
        }
    }

    class DummyLogoutUseCase: LogoutUseCase {
        override suspend fun logout() {}
    }

    // endregion
}
