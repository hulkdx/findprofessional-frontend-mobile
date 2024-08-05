package com.hulkdx.findprofessional.feature.pro.auth.signup

import com.hulkdx.findprofessional.core.model.proauth.SignUpProRequest
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.core.utils.generalError

class SignUpProUseCase(
    private val api: SignUpProApi,
) {
    suspend fun register(request: SignUpProRequest): StringOrRes? = try {
        api.register(request)
        null
    } catch (e: Exception) {
        e.generalError()
    }
}
