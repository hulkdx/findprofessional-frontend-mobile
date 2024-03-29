package com.hulkdx.findprofessional.common.feature.home

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalReview
import com.hulkdx.findprofessional.common.utils.auth
import io.ktor.client.HttpClient
import io.ktor.client.call.body
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
    private val accessTokenStorage: AccessTokenStorage,
) : ProfessionalApi {

    override suspend fun findAll(): List<Professional> {
        return client.get {
            auth(accessTokenStorage)
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
            auth(accessTokenStorage)
            url("professional/$professionalId/review?page=$page&pageSize=$pageSize")
            contentType(ContentType.Application.Json)
        }
            .body()
    }
}
