package com.hulkdx.findprofessional.common.feature.authentication.signup

import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest


class SignUpUseCase(
    private val signUpApi: SignUpApi,
) {
    suspend fun register(request: RegisterRequest) = signUpApi.register(request)
}
