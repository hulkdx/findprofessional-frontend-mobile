package com.hulkdx.findprofessional.common.config.storage

import com.hulkdx.findprofessional.common.feature.authentication.model.User

interface UserStorage {
    suspend fun get(): User?
    suspend fun set(value: User)
    suspend fun remove()
}
