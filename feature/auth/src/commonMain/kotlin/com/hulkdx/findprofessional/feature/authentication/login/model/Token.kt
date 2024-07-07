package com.hulkdx.findprofessional.feature.authentication.login.model

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val accessToken: String,
    val refreshToken: String,
)
