package com.hulkdx.findprofessional.common.feature.authentication.login

import com.hulkdx.findprofessional.common.feature.authentication.model.Auth
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

interface RefreshTokenApi {
    suspend fun refreshToken(refreshToken: String, accessToken: String): Auth
}

class RefreshTokenApiImpl(
    private val client: HttpClient,
) : RefreshTokenApi {
    override suspend fun refreshToken(
        refreshToken: String,
        accessToken: String,
    ): Auth {
        return client.post {
            url(urlString)
            contentType(ContentType.Application.Json)
            setBody(RefreshRequest(refreshToken))
            headers {
                append(HttpHeaders.Authorization, "Bearer $accessToken")
            }
        }
            .body()
    }

    companion object {
        const val urlString = "auth/refresh"
    }
}

@Serializable
private data class RefreshRequest(val refreshToken: String)
