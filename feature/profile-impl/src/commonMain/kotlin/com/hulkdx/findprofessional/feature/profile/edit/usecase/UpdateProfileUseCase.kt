package com.hulkdx.findprofessional.feature.profile.edit.usecase

import com.hulkdx.findprofessional.feature.authentication.model.user.User
import com.hulkdx.findprofessional.feature.authentication.storage.UserStorage
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.core.utils.getGeneralErrorOrNull
import com.hulkdx.findprofessional.feature.profile.edit.api.UpdateProfileApi

class UpdateProfileUseCase(
    private val api: UpdateProfileApi,
    private val userStorage: UserStorage,
) {
    suspend fun execute(user: User): StringOrRes? = getGeneralErrorOrNull {
        val updatedUser = api.update(user)
        userStorage.set(
            requireNotNull(userStorage.get()?.copy(user = updatedUser))
        )
    }
}
