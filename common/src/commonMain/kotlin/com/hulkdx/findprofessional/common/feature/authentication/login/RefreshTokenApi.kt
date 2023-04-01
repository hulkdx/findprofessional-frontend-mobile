package com.hulkdx.findprofessional.common.feature.authentication.login

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

interface RefreshTokenApi {
    suspend fun refreshToken(refreshToken: String, accessToken: String): AuthToken
}

class RefreshTokenApiImpl(
    private val client: HttpClient,
) : RefreshTokenApi {
    override suspend fun refreshToken(
        refreshToken: String,
        accessToken: String,
    ): AuthToken {
        return client.post {
            url("auth/refresh")
            contentType(ContentType.Application.Json)
            setBody(RefreshRequest(refreshToken))
            headers {
                append(HttpHeaders.Authorization, "Bearer $accessToken")
            }
        }
            .body()
    }
}

private data class RefreshRequest(val refreshToken: String)
