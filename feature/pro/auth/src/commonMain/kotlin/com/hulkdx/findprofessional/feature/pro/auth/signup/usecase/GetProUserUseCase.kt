package com.hulkdx.findprofessional.feature.pro.auth.signup.usecase

import com.hulkdx.findprofessional.core.model.user.ProUser
import com.hulkdx.findprofessional.core.storage.UserStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GetProUserUseCase {
    fun getUser(): Flow<ProUser?>
}

class GetProUserUseCaseImpl(
    private val userStorage: UserStorage,
) : GetProUserUseCase {
    override fun getUser(): Flow<ProUser?> {
        return userStorage.getFlow()
            .map { it?.user as? ProUser }
    }
}
