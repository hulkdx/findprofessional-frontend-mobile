package com.hulkdx.findprofessional.feature.mybookings.api

import com.hulkdx.findprofessional.core.network.apiUrl
import com.hulkdx.findprofessional.feature.authentication.storage.UserStorage
import com.hulkdx.findprofessional.feature.authentication.storage.auth
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class MyBookingApiImpl(
    private val client: HttpClient,
    private val userStorage: UserStorage,
) : MyBookingApi {
    override suspend fun getMyBookingsList(): MyBookingsResponse {
        return client.get {
            apiUrl("TODO")
            auth(userStorage)
            contentType(ContentType.Application.Json)
        }
            .body()
    }
}
