package com.hulkdx.findprofessional.feature.review

import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalReview
import com.hulkdx.findprofessional.core.network.apiUrl
import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.core.utils.auth
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface ReviewApi {
    suspend fun findAll(professionalId: Long, page: Int, pageSize: Int): List<ProfessionalReview>
}

class ReviewApiImpl(
    private val client: HttpClient,
    private val userStorage: UserStorage,
) : ReviewApi {

    override suspend fun findAll(
        professionalId: Long,
        page: Int,
        pageSize: Int,
    ): List<ProfessionalReview> {
        return client.get {
            auth(userStorage)
            apiUrl("professional/$professionalId/review?page=$page&pageSize=$pageSize")
            contentType(ContentType.Application.Json)
        }
            .body()
    }
}
