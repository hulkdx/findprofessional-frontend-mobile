package com.hulkdx.findprofessional.feature.pro.auth.signup.model

import kotlinx.serialization.Serializable

@Serializable
data class ProRegisterRequest(
    val email: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val skypeId: String = "",
    val aboutMe: String = "",
    val coachType: String = "",
    val price: String = "",
    val priceCurrency: String = "",
    val webcamConsentChecked: Boolean = false,
    val idConsentChecked: Boolean = false,
)
