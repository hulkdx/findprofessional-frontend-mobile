package com.hulkdx.findprofessional.common.config.api.interceptor

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.config.storage.RefreshTokenStorage
import com.hulkdx.findprofessional.common.feature.authentication.login.LoginApiImpl
import com.hulkdx.findprofessional.common.feature.authentication.login.RefreshTokenApi
import com.hulkdx.findprofessional.common.feature.authentication.login.RefreshTokenApiImpl
import com.hulkdx.findprofessional.common.feature.authentication.logout.LogoutUseCase
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpApiImpl
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.Unauthorized

class TokenInterceptor(
    private val api: RefreshTokenApi,
    private val accessTokenStorage: AccessTokenStorage,
    private val refreshTokenStorage: RefreshTokenStorage,
    private val logoutUseCase: LogoutUseCase,
) : AppInterceptor {

    private val filterUrl = listOf(
        "/${RefreshTokenApiImpl.urlString}",
        "/${LoginApiImpl.urlString}",
        "/${SignUpApiImpl.urlString}",
    )

    override suspend fun intercept(sender: Sender, request: HttpRequestBuilder): HttpClientCall {
        val original = sender.execute(request)

        val requestUrl = original.request.url.encodedPath
        if (filterUrl.contains(requestUrl)) {
            return original
        }

        if (!original.request.hasAuthorizationHeader()) {
            return original
        }

        if (original.response.status == Unauthorized) {
            val accessToken = accessTokenStorage.get()
            val refreshToken = refreshTokenStorage.get()
            if (accessToken == null || refreshToken == null) {
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
            accessTokenStorage.set(newAccessToken)
            refreshTokenStorage.set(newRefreshToken)

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
