package com.hulkdx.findprofessional.feature.authentication.login.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val firstName: String,
    val lastName: String,
    val profileImage: String? = null,
    val skypeId: String? = null,
) {
    val fullName: String
        get() = "$firstName $lastName"
}
