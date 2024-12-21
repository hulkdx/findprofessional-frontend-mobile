package com.hulkdx.findprofessional.feature.pro.profile.edit

import com.hulkdx.findprofessional.core.model.user.ProUser
import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.core.utils.getGeneralErrorOrNull
import com.hulkdx.findprofessional.feature.pro.auth.signup.api.SignUpProApi

class SaveProUserUseCase(
    private val api: SignUpProApi,
    private val userStorage: UserStorage,
) {
    suspend fun save(user: ProUser) = getGeneralErrorOrNull {
        api.update(user)
        userStorage.set(
            requireNotNull(userStorage.get()?.copy(user = user))
        )
    }
}
