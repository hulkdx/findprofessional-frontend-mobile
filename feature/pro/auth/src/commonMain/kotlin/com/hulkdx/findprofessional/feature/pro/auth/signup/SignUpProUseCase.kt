package com.hulkdx.findprofessional.feature.pro.auth.signup

import com.hulkdx.findprofessional.core.model.proauth.SignUpProRequest
import com.hulkdx.findprofessional.core.utils.StringOrRes

class SignUpProUseCase(
    private val api: SignUpProApi,
) {
    suspend fun register(request: SignUpProRequest): StringOrRes? {
        TODO()
    }
}
