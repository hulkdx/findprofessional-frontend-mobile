package com.hulkdx.findprofessional.core.storage

import com.hulkdx.findprofessional.core.features.user.ProUser
import com.hulkdx.findprofessional.core.features.user.User
import com.hulkdx.findprofessional.core.features.user.UserData
import com.hulkdx.findprofessional.core.utils.exception.AccessTokenNotFoundException
import kotlinx.coroutines.flow.Flow


interface UserStorage {
    suspend fun get(): UserData?
    suspend fun set(value: UserData)
    suspend fun remove()

    fun getFlow(): Flow<UserData?>
}

suspend fun UserStorage.getNormalUser(): User? = get()?.user as? User
suspend fun UserStorage.getProUser(): ProUser? = get()?.user as? ProUser
suspend fun UserStorage.getAccessToken() =
    get()?.token?.accessToken ?: throw AccessTokenNotFoundException()
