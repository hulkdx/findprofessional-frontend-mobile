package com.hulkdx.findprofessional.feature.authentication.login.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String,
)
