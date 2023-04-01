package com.hulkdx.findprofessional.common.config.api.interceptor

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.config.storage.RefreshTokenStorage
import com.hulkdx.findprofessional.common.feature.authentication.login.RefreshTokenApi
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.HttpStatusCode.Companion.Unauthorized

class TokenInterceptor(
    private val api: RefreshTokenApi,
    private val accessTokenStorage: AccessTokenStorage,
    private val refreshTokenStorage: RefreshTokenStorage,
) : AppInterceptor {

    override suspend fun intercept(sender: Sender, request: HttpRequestBuilder): HttpClientCall {
        val original = sender.execute(request)
        if (original.response.status == Unauthorized) {
            val accessToken = accessTokenStorage.get()
            val refreshToken = refreshTokenStorage.get()
            if (accessToken == null || refreshToken == null) {
                logout()
                return original
            }

            val (newAccessToken, newRefreshToken) = api.refreshToken(accessToken, refreshToken)
            accessTokenStorage.set(newAccessToken)
            refreshTokenStorage.set(newRefreshToken)

            val retry = sender.execute(request)
            if (retry.response.status == Unauthorized) {
                logout()
            }
            return retry
        }
        return original
    }

    private fun logout() {
        // TODO:
    }

}
