package com.hulkdx.findprofessional.core.storage

import com.hulkdx.findprofessional.core.model.user.User
import com.hulkdx.findprofessional.core.model.user.UserData


interface UserStorage {
    suspend fun get(): UserData?
    suspend fun set(value: UserData)
    suspend fun remove()
}

suspend fun UserStorage.getNormalUser(): User? = get()?.user as? User
