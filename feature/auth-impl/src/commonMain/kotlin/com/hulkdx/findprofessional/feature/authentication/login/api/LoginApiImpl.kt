package com.hulkdx.findprofessional.feature.authentication.login.api

import com.hulkdx.findprofessional.core.network.apiUrl
import com.hulkdx.findprofessional.feature.authentication.login.model.LoginRequest
import com.hulkdx.findprofessional.feature.authentication.model.user.UserData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class LoginApiImpl(
    private val client: HttpClient,
) : LoginApi {
    override suspend fun login(request: LoginRequest): UserData {
        return client.post {
            apiUrl(URL)
            contentType(ContentType.Application.Json)
            setBody(request)
        }
            .body()
    }

    companion object {
        const val URL = "auth/login"
    }
}
