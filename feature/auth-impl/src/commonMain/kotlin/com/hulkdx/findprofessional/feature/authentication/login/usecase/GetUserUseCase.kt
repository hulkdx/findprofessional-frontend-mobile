package com.hulkdx.findprofessional.feature.authentication.login.usecase

import com.hulkdx.findprofessional.feature.authentication.model.user.UserType
import com.hulkdx.findprofessional.core.storage.UserStorage


class GetUserUseCase(
    private val userStorage: UserStorage,
) {
    suspend fun execute(): UserType? {
        return userStorage.get()?.user
    }
}
