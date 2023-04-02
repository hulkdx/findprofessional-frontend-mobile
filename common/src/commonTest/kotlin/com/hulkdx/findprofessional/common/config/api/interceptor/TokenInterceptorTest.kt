@file:OptIn(InternalAPI::class)

package com.hulkdx.findprofessional.common.config.api.interceptor

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.config.storage.RefreshTokenStorage
import com.hulkdx.findprofessional.common.feature.authentication.login.AuthToken
import com.hulkdx.findprofessional.common.feature.authentication.login.RefreshTokenApi
import com.hulkdx.findprofessional.common.utils.KoinTestUtil
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.util.date.*
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
            response(responseStatusCode = HttpStatusCode.Unauthorized)
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
    }

    private class RefreshTokenApiMock : RefreshTokenApi {
        var isRefreshTokenCalled = false

        override suspend fun refreshToken(refreshToken: String, accessToken: String): AuthToken {
            isRefreshTokenCalled = true
            return AuthToken("access", "refresh")
        }
    }

    private class SenderMock : Sender {
        private var response: HttpClientCall? = null
        var isExecuteCalledCount = 0

        override suspend fun execute(requestBuilder: HttpRequestBuilder): HttpClientCall {
            isExecuteCalledCount += 1
            return response!!
        }

        suspend fun response(
            request: HttpRequestBuilder.() -> Unit = {},
            responseStatusCode: HttpStatusCode,
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

    // endregion
}