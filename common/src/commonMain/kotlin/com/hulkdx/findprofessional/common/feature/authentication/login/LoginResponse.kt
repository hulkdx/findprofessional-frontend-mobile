package com.hulkdx.findprofessional.common.feature.authentication.login

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
)
