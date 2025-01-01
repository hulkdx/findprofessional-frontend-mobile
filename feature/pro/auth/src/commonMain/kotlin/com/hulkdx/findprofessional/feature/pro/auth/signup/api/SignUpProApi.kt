package com.hulkdx.findprofessional.feature.pro.auth.signup.api

import com.hulkdx.findprofessional.core.utils.auth
import com.hulkdx.findprofessional.core.features.proauth.SignUpProRequest
import com.hulkdx.findprofessional.core.features.user.ProUser
import com.hulkdx.findprofessional.core.features.user.UserData
import com.hulkdx.findprofessional.core.storage.UserStorage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
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
    private val userStorage: UserStorage,
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
        client.post {
            auth(userStorage)
            url("professional")
            contentType(ContentType.Application.Json)
            setBody(proUser)
        }
    }
}
