package com.hulkdx.findprofessional.common.feature.authentication.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val firstName: String,
    val lastName: String,
    val profileImage: String?,
)
