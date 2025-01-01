package com.hulkdx.findprofessional.feature.home.main.api

import com.hulkdx.findprofessional.core.utils.auth
import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.core.storage.UserStorage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface ProfessionalApi {
    suspend fun findAll(): List<Professional>
}

class ProfessionalApiImpl(
    private val client: HttpClient,
    private val userStorage: UserStorage,
) : ProfessionalApi {

    override suspend fun findAll(): List<Professional> {
        return client.get {
            auth(userStorage)
            url("professional")
            contentType(ContentType.Application.Json)
        }
            .body()
    }
}
