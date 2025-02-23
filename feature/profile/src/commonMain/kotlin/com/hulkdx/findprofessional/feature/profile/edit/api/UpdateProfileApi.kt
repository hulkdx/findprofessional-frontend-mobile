package com.hulkdx.findprofessional.feature.profile.edit.api

import com.hulkdx.findprofessional.core.features.user.User
import com.hulkdx.findprofessional.core.features.user.UserType
import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.core.utils.auth
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface UpdateProfileApi {
    suspend fun update(user: User): UserType
}

class UpdateProfileApiImpl(
    private val client: HttpClient,
    private val userStorage: UserStorage,
): UpdateProfileApi {
    override suspend fun update(user: User): UserType {
        return client.post {
            auth(userStorage)
            url("/auth/user")
            contentType(ContentType.Application.Json)
            setBody(user)
        }.body()
    }
}
