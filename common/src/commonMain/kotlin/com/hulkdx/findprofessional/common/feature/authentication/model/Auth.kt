package com.hulkdx.findprofessional.common.feature.authentication.model

import kotlinx.serialization.Serializable

@Serializable
data class Auth(
    val token: Token,
    val user: User,
)
