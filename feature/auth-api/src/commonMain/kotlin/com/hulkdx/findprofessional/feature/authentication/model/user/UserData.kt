package com.hulkdx.findprofessional.feature.authentication.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val token: Token,
    val user: UserType,
)
