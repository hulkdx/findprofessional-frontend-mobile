package com.hulkdx.findprofessional.core.features.user

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val token: Token,
    val user: UserType,
)
