package com.hulkdx.findprofessional.feature.pro.auth.signup.usecase

import com.hulkdx.findprofessional.feature.authentication.model.user.ProUser
import com.hulkdx.findprofessional.feature.authentication.storage.UserStorage
import com.hulkdx.findprofessional.feature.authentication.storage.getProUser
import com.hulkdx.findprofessional.feature.pro.auth.GetProUserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


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
