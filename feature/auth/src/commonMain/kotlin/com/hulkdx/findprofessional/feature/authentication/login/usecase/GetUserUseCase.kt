package com.hulkdx.findprofessional.feature.authentication.login.usecase

import com.hulkdx.findprofessional.core.model.user.User
import com.hulkdx.findprofessional.core.storage.UserStorage


class GetUserUseCase(
    private val userStorage: UserStorage,
) {
    suspend fun execute(): User? {
        return userStorage.get()?.user as? User
    }
}
