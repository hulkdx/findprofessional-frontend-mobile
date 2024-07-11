package com.hulkdx.findprofessional.feature.authentication.signup

import com.hulkdx.findprofessional.feature.authentication.login.model.UserData
import com.hulkdx.findprofessional.feature.authentication.signup.model.RegisterRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface SignUpApi {
    suspend fun register(request: RegisterRequest): UserData
}

class SignUpApiImpl(
    private val client: HttpClient,
) : SignUpApi {

    override suspend fun register(request: RegisterRequest): UserData {
        return client.post {
            url(URL)
            contentType(ContentType.Application.Json)
            setBody(request)
        }
            .body()
    }

    companion object {
        const val URL = "auth/register"
    }
}
