package com.hulkdx.findprofessional.common.feature.authentication.signup

import com.hulkdx.findprofessional.common.feature.authentication.model.Auth
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface SignUpApi {
    suspend fun register(request: RegisterRequest): Auth
}

class SignUpApiImpl(
    private val client: HttpClient,
) : SignUpApi {

    override suspend fun register(request: RegisterRequest): Auth {
        return client.post {
            url(urlString)
            contentType(ContentType.Application.Json)
            setBody(request)
        }
            .body()
    }

    companion object {
        const val urlString = "auth/register"
    }
}
