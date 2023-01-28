package com.hulkdx.findprofessional.common.feature.authentication.signup


class SignUpUseCase(
    private val signUpApi: SignUpApi,
) {
    suspend fun register() = signUpApi.register()
}
