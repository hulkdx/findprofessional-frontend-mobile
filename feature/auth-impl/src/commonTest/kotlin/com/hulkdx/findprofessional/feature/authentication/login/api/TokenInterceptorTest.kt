@file:OptIn(InternalAPI::class, ExperimentalCoroutinesApi::class)

package com.hulkdx.findprofessional.feature.authentication.login.api

import com.hulkdx.findprofessional.feature.authentication.model.user.Token
import com.hulkdx.findprofessional.feature.authentication.model.user.UserData
import com.hulkdx.findprofessional.feature.authentication.login.usecase.LogoutUseCase
import com.hulkdx.findprofessional.libs.common.tests.StubUserStorage
import com.hulkdx.findprofessional.libs.common.tests.newUserData
import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.Sender
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpResponseData
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.util.date.GMTDate
import io.ktor.utils.io.InternalAPI
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
    private val userStorage = object : StubUserStorage() {
        var value: UserData? = null
        var isSetCalled = false

        override suspend fun get(): UserData? {
            return value
        }

        override suspend fun set(value: UserData) {
            isSetCalled = true
            this.value = value
        }
    }

    @BeforeTest
    fun setUp() {
        sut = TokenInterceptor(
            refreshTokenApi,
            userStorage,
            DummyLogoutUseCase()
        )
    }

    @AfterTest
    fun tearDown() {
    }

    @Test
    fun `intercept unauthorized when userStorage is not empty then should store and refresh token and retry call`() =
        runTest {
            // Arrange
            userStorage.value = newUserData()
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
            assertEquals(true, userStorage.isSetCalled)
            val isRetriedCall = (sender.isExecuteCalledCount == 2)
            assertEquals(true, isRetriedCall)
        }

    @Test
    fun `don't intercept filtered url`() = runTest {
        // Arrange
        val filterUrl = "/auth/refresh"

        userStorage.value = newUserData()
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
        assertEquals(false, userStorage.isSetCalled)
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
        userStorage.value = newUserData()
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
        assertEquals(false, userStorage.isSetCalled)
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

        userStorage.value = newUserData()
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

    class DummyLogoutUseCase : LogoutUseCase {
        override suspend fun logout() {}
    }

    // endregion
}
