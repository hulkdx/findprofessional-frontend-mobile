package com.hulkdx.findprofessional.feature.pro.auth.signup.api

import com.hulkdx.findprofessional.core.model.proauth.SignUpProRequest
import com.hulkdx.findprofessional.core.model.user.ProUser
import com.hulkdx.findprofessional.core.model.user.UserData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface SignUpProApi {
    suspend fun register(request: SignUpProRequest): UserData
    suspend fun update(proUser: ProUser)
}

class SignUpProApiImpl(
    private val client: HttpClient,
) : SignUpProApi {
    override suspend fun register(request: SignUpProRequest): UserData {
        return client.put {
            url("professional")
            contentType(ContentType.Application.Json)
            setBody(request)
        }
            .body()
    }

    override suspend fun update(proUser: ProUser) {
    }
}
