package com.hulkdx.findprofessional.common.feature.authentication.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
)
