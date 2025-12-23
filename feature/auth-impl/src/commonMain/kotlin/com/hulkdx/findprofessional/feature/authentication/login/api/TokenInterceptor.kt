package com.hulkdx.findprofessional.feature.authentication.login.api

import com.hulkdx.findprofessional.feature.authentication.model.user.Token
import com.hulkdx.findprofessional.feature.authentication.storage.UserStorage
import com.hulkdx.findprofessional.feature.authentication.login.usecase.LogoutUseCase
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpApiImpl
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.Sender
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode.Companion.Unauthorized

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

    suspend fun intercept(sender: Sender, request: HttpRequestBuilder): HttpClientCall {
        val original = sender.execute(request)

        val requestUrl = original.request.url.encodedPath
        if (filterUrl.contains(requestUrl)) {
            return original
        }

        if (!original.request.hasAuthorizationHeader()) {
            return original
        }

        if (original.response.status == Unauthorized) {
            val userData = userStorage.get()
            val accessToken = userData?.token?.accessToken
            val refreshToken = userData?.token?.refreshToken
            if (userData == null || accessToken == null || refreshToken == null) {
                logoutUseCase.logout()
                return original
            }
            val response = try {
                api.refreshToken(refreshToken, accessToken)
            } catch (e: ClientRequestException) {
                if (e.response.status == Unauthorized) {
                    logoutUseCase.logout()
                    return original
                } else {
                    throw e
                }
            }
            val newAccessToken = response.accessToken
            val newRefreshToken = response.refreshToken
            userStorage.set(userData.copy(token = Token(newAccessToken, newRefreshToken)))

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

    private fun HttpRequest.hasAuthorizationHeader(): Boolean {
        val authorizationHeader = headers[HttpHeaders.Authorization] ?: ""
        return authorizationHeader.isNotEmpty()
    }
}
