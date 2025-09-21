package com.hulkdx.findprofessional.feature.book.summery.api

import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.core.utils.auth
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface PaymentApi {
    suspend fun checkout(request: PayRequest): PayResponse
}

class PaymentApiImpl(
    private val client: HttpClient,
    private val userStorage: UserStorage,
) : PaymentApi {
    override suspend fun checkout(request: PayRequest): PayResponse {
        return client.post {
            auth(userStorage)
            url("/payments/create-intent")
            contentType(ContentType.Application.Json)
            setBody(request)
        }
            .body()
    }
}

