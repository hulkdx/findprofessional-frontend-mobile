package com.hulkdx.findprofessional.feature.authentication.login.api

import com.hulkdx.findprofessional.feature.authentication.login.usecase.LogoutUseCase
import com.hulkdx.findprofessional.feature.authentication.model.user.Token
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpApiImpl
import com.hulkdx.findprofessional.feature.authentication.storage.UserStorage
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.Sender
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class TokenInterceptor(
    private val api: RefreshTokenApi,
    private val userStorage: UserStorage,
    private val logoutUseCase: LogoutUseCase,
) {

    private val filterUrl = listOf(
        "/${RefreshTokenApiImpl.URL}",
        "/${LoginApiImpl.URL}",
        "/${SignUpApiImpl.URL}",
    )
    private val mutex = Mutex()

    suspend fun intercept(sender: Sender, request: HttpRequestBuilder): HttpClientCall {
        val original = sender.execute(request)

        val requestUrl = original.request.url.encodedPath
        if (filterUrl.contains(requestUrl)) {
            return original
        }

        val originalAccessToken = original.request.getAccessToken()
        if (originalAccessToken.isEmpty()) {
            return original
        }

        if (original.response.status == Unauthorized) {
            val newAccessToken = mutex.withLock {
                val userData = userStorage.get()
                val accessToken = userData?.token?.accessToken
                val refreshToken = userData?.token?.refreshToken

                when {
                    userData == null || accessToken == null || refreshToken == null -> {
                        logoutUseCase.logout()
                        return original
                    }

                    accessToken != originalAccessToken -> accessToken

                    else -> {
                        val new = try {
                            api.refreshToken(refreshToken, accessToken)
                        } catch (e: ClientRequestException) {
                            if (e.response.status == Unauthorized) {
                                logoutUseCase.logout()
                                return original
                            } else {
                                throw e
                            }
                        }
                        userStorage.set(
                            userData.copy(
                                token = Token(
                                    new.accessToken,
                                    new.refreshToken
                                )
                            )
                        )
                        new.accessToken
                    }
                }
            }

            val retry = sender.execute(request.replaceAuthHeader(newAccessToken))
            if (retry.response.status == Unauthorized) {
                logoutUseCase.logout()
            }
            return retry
        }
        return original
    }

    private fun HttpRequestBuilder.replaceAuthHeader(accessToken: String): HttpRequestBuilder {
        headers {
            val authType = get(HttpHeaders.Authorization)?.split(" ")?.get(0)
            remove(HttpHeaders.Authorization)
            append(HttpHeaders.Authorization, "$authType $accessToken")
        }
        return this
    }

    private fun HttpRequest.getAccessToken(): String {
        val header = headers[HttpHeaders.Authorization] ?: return ""
        // drop "Bearer ", keep the raw token
        return header.substringAfter(" ")
    }
}
