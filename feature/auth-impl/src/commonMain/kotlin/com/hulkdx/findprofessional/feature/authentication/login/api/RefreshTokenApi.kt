package com.hulkdx.findprofessional.feature.authentication.login.api

import com.hulkdx.findprofessional.feature.authentication.model.user.Token
import com.hulkdx.findprofessional.core.network.apiUrl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.serialization.Serializable

interface RefreshTokenApi {
    suspend fun refreshToken(refreshToken: String, accessToken: String): Token
}

class RefreshTokenApiImpl(
    private val client: HttpClient,
) : RefreshTokenApi {
    override suspend fun refreshToken(
        refreshToken: String,
        accessToken: String,
    ): Token {
        return client.post {
            apiUrl(URL)
            contentType(ContentType.Application.Json)
            setBody(RefreshRequest(refreshToken))
            headers {
                append(HttpHeaders.Authorization, "Bearer $accessToken")
            }
        }
            .body()
    }

    companion object {
        const val URL = "auth/refresh"
    }
}

@Serializable
private data class RefreshRequest(val refreshToken: String)
