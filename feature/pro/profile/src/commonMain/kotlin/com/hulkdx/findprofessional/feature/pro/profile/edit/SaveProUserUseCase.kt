package com.hulkdx.findprofessional.feature.pro.profile.edit

import com.hulkdx.findprofessional.core.model.user.ProUser
import com.hulkdx.findprofessional.core.utils.getGeneralErrorOrNull
import com.hulkdx.findprofessional.feature.pro.auth.signup.api.SignUpProApi

class SaveProUserUseCase(
    private val api: SignUpProApi,
) {
    suspend fun save(user: ProUser) = getGeneralErrorOrNull {
        api.update(user)
    }
}
