package com.hulkdx.findprofessional.feature.pro.auth.signup.usecase

import com.hulkdx.findprofessional.core.model.user.ProUser
import com.hulkdx.findprofessional.feature.pro.auth.signup.api.SignUpProApi

class SaveProUserUseCase(
    private val api: SignUpProApi,
) {
    fun save(value: ProUser) {
    }
}
