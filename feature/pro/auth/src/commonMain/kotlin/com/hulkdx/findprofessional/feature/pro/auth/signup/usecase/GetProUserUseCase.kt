package com.hulkdx.findprofessional.feature.pro.auth.signup.usecase

import com.hulkdx.findprofessional.core.features.user.ProUser
import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.core.storage.getProUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GetProUserUseCase {
    fun getUserFlow(): Flow<ProUser?>
    suspend fun getUser(): ProUser?
}

class GetProUserUseCaseImpl(
    private val userStorage: UserStorage,
) : GetProUserUseCase {
    override fun getUserFlow(): Flow<ProUser?> {
        return userStorage.getFlow()
            .map { it?.user as? ProUser }
    }

    override suspend fun getUser(): ProUser? {
        return userStorage.getProUser()
    }
}
