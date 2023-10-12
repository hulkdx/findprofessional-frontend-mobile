package com.hulkdx.findprofessional.common.feature.home

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.utils.getAuth
import io.ktor.client.HttpClient

interface ProfessionalApi {
    suspend fun findAll(): List<Professional>
}

class ProfessionalApiImpl(
    private val client: HttpClient,
    private val accessTokenStorage: AccessTokenStorage,
) : ProfessionalApi {

    override suspend fun findAll(): List<Professional> {
        return client.getAuth(accessTokenStorage, "professional")
    }
}
