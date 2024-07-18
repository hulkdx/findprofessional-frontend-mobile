package com.hulkdx.findprofessional.feature.authentication.login.usecase

import com.hulkdx.findprofessional.feature.authentication.login.model.User
import com.hulkdx.findprofessional.feature.authentication.login.storage.UserStorage


class GetUserUseCase(
    private val userStorage: UserStorage,
) {
    suspend fun execute(): User? {
        return userStorage.get()?.user
    }
}
