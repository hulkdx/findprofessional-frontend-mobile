package com.hulkdx.findprofessional.feature.pro.auth.signup

import com.hulkdx.findprofessional.core.model.proauth.SignUpProRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface SignUpProApi {
    suspend fun register(request: SignUpProRequest)
}

class SignUpProApiImpl(
    private val client: HttpClient,
) : SignUpProApi {

    override suspend fun register(request: SignUpProRequest) {
        return client.put {
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
