package com.hulkdx.findprofessional.common.feature.authentication.login

import kotlinx.serialization.Serializable

@Serializable
data class AuthToken(
    val accessToken: String,
    val refreshToken: String,
)
