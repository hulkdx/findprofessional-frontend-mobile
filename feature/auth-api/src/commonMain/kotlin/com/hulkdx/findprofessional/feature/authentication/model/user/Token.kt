package com.hulkdx.findprofessional.feature.authentication.model.user

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val accessToken: String,
    val refreshToken: String,
)
