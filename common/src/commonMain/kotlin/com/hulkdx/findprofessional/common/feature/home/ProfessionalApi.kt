package com.hulkdx.findprofessional.common.feature.home

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.utils.getAuth
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface ProfessionalApi {
    suspend fun findAll(): List<Professional>
}

class ProfessionalApiImpl(
    private val client: HttpClient,
    private val accessTokenStorage: AccessTokenStorage,
) : ProfessionalApi {

    override suspend fun findAll(): List<Professional> {
        return client.getAuth(accessTokenStorage, urlString)
    }

    companion object {
        const val urlString = "http://10.0.2.2:8081/professional"
    }
}
