package com.hulkdx.findprofessional.common.config.storage

interface RefreshTokenStorage {
    suspend fun get(): String?
    suspend fun set(value: String)
    suspend fun remove()
}
