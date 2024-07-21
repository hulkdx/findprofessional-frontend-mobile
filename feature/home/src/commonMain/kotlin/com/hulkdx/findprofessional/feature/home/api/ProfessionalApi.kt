package com.hulkdx.findprofessional.feature.home.api

import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.core.model.pro.ProfessionalReview
import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.core.utils.AccessTokenNotFoundException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface ProfessionalApi {
    suspend fun findAll(): List<Professional>
    suspend fun findAllReviews(
        professionalId: Int,
        page: Int,
        pageSize: Int,
    ): List<ProfessionalReview>
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

    override suspend fun findAllReviews(
        professionalId: Int,
        page: Int,
        pageSize: Int,
    ): List<ProfessionalReview> {
        return client.get {
            auth(userStorage)
            url("professional/$professionalId/review?page=$page&pageSize=$pageSize")
            contentType(ContentType.Application.Json)
        }
            .body()
    }
}

suspend inline fun HttpRequestBuilder.auth(userStorage: UserStorage) {
    val accessToken = userStorage.get()?.token?.accessToken ?: throw AccessTokenNotFoundException()
    bearerAuth(accessToken)
}
