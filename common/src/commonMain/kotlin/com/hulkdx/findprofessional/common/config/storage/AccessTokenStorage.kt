package com.hulkdx.findprofessional.common.config.storage

interface AccessTokenStorage {
    suspend fun get(): String?
    suspend fun set(value: String)
    suspend fun remove()
}
