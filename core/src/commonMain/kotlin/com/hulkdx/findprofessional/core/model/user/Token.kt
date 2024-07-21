package com.hulkdx.findprofessional.core.model.user

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val accessToken: String,
    val refreshToken: String,
)
