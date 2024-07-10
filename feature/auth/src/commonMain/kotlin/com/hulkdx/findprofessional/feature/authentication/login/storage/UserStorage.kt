package com.hulkdx.findprofessional.feature.authentication.login.storage

import com.hulkdx.findprofessional.feature.authentication.login.model.UserData


interface UserStorage {
    suspend fun get(): UserData?
    suspend fun set(value: UserData)
    suspend fun remove()
}
