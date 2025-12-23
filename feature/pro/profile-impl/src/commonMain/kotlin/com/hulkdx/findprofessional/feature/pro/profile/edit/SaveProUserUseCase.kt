package com.hulkdx.findprofessional.feature.pro.profile.edit

import com.hulkdx.findprofessional.core.features.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.core.features.user.ProUser
import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.core.utils.getGeneralErrorOrNull

class SaveProUserUseCase(
    private val api: ProfessionalApi,
    private val userStorage: UserStorage,
) {
    suspend fun save(user: ProUser) = getGeneralErrorOrNull {
        api.update(user)
        userStorage.set(
            requireNotNull(userStorage.get()?.copy(user = user))
        )
    }
}
