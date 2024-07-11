package com.hulkdx.findprofessional.feature.authentication.signup.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String,
)
