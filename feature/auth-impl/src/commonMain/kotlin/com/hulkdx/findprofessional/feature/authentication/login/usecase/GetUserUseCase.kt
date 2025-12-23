package com.hulkdx.findprofessional.feature.authentication.login.usecase

import com.hulkdx.findprofessional.feature.authentication.model.user.UserType
import com.hulkdx.findprofessional.feature.authentication.storage.UserStorage


class GetUserUseCaseImpl(
    private val userStorage: UserStorage,
): GetUserUseCase {
    override suspend fun execute(): UserType? {
        return userStorage.get()?.user
    }
}
