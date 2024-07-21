package com.hulkdx.findprofessional.core.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val token: Token,
    val user: User,
)
