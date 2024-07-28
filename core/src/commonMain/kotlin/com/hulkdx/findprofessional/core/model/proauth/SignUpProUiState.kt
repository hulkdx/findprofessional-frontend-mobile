package com.hulkdx.findprofessional.core.model.proauth

import kotlinx.serialization.Serializable

@Serializable
data class SignUpProUiState(
    val step: Int = 1,
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
