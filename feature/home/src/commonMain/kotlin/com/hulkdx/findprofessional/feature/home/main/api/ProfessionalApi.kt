package com.hulkdx.findprofessional.feature.home.main.api

import com.hulkdx.findprofessional.core.utils.auth
import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.features.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.core.features.pro.model.request.SignUpProRequest
import com.hulkdx.findprofessional.core.features.pro.model.request.UpdateAvailabilityRequest
import com.hulkdx.findprofessional.core.features.user.ProUser
import com.hulkdx.findprofessional.core.features.user.UserData
import com.hulkdx.findprofessional.core.storage.UserStorage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

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

    override suspend fun getAvailability(): List<ProfessionalAvailability> {
        return client.get {
            auth(userStorage)
            url("professional/availability")
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun updateAvailability(request: UpdateAvailabilityRequest) {
        client.post {
            auth(userStorage)
            url("professional/availability")
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }
}
