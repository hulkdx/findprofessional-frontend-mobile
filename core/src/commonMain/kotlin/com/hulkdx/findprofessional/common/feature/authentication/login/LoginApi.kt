package com.hulkdx.findprofessional.common.feature.authentication.login

import com.hulkdx.findprofessional.common.feature.authentication.model.Auth
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.LoginRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface LoginApi {
    suspend fun login(request: LoginRequest): Auth
}

class LoginApiImpl(
    private val client: HttpClient,
) : LoginApi {
    override suspend fun login(request: LoginRequest): Auth {
        return client.post {
            url(urlString)
            contentType(ContentType.Application.Json)
            setBody(request)
        }
            .body()
    }

    companion object {
        const val urlString = "auth/login"
    }
}
