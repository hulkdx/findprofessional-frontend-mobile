package com.hulkdx.findprofessional.feature.authentication.login.model

import kotlinx.serialization.Serializable

@Serializable
data class Auth(
    val token: Token,
    val user: User,
)
