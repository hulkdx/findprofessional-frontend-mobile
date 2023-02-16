package com.hulkdx.findprofessional.common.feature.authentication.signup.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(val email: String, val password: String)
