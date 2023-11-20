package com.hulkdx.findprofessional.common.feature.authentication.signup.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val profileImage: String?,
)
