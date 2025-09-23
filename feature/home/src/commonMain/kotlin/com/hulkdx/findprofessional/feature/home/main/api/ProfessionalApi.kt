package com.hulkdx.findprofessional.feature.home.main.api

import com.hulkdx.findprofessional.core.features.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.features.pro.model.request.CreateBookingRequest
import com.hulkdx.findprofessional.core.features.pro.model.request.SignUpProRequest
import com.hulkdx.findprofessional.core.features.pro.model.request.UpdateAvailabilityRequest
import com.hulkdx.findprofessional.core.features.pro.model.response.CreateBookingResponse
import com.hulkdx.findprofessional.core.features.user.ProUser
import com.hulkdx.findprofessional.core.features.user.UserData
import com.hulkdx.findprofessional.core.network.apiUrl
import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.core.utils.auth
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ProfessionalApiImpl(
    private val client: HttpClient,
    private val userStorage: UserStorage,
) : ProfessionalApi {

    override suspend fun findAll(): List<Professional> {
        return client.get {
            auth(userStorage)
            apiUrl("professional")
            contentType(ContentType.Application.Json)
        }
            .body()
    }

    override suspend fun register(request: SignUpProRequest): UserData {
        return client.put {
            apiUrl("professional")
            contentType(ContentType.Application.Json)
            setBody(request)
        }
            .body()
    }

    override suspend fun update(proUser: ProUser) {
        client.post {
            auth(userStorage)
            apiUrl("professional")
            contentType(ContentType.Application.Json)
            setBody(proUser)
        }
    }

    override suspend fun getAvailability(): List<ProfessionalAvailability> {
        return client.get {
            auth(userStorage)
            apiUrl("professional/availability")
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun updateAvailability(request: UpdateAvailabilityRequest) {
        client.post {
            auth(userStorage)
            apiUrl("professional/availability")
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    override suspend fun createBooking(request: CreateBookingRequest): CreateBookingResponse {
        return client.post {
            auth(userStorage)
            apiUrl("payments/create-intent")
            contentType(ContentType.Application.Json)
            setBody(request)
        }
            .body()
    }
}
