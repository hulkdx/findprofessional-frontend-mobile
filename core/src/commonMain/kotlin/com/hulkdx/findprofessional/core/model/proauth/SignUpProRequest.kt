package com.hulkdx.findprofessional.core.model.proauth

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class SignUpProRequest(
    @Transient val step: Int = 1,
    val email: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val skypeId: String = "",
    val aboutMe: String = "",
    val coachType: String = "",
    val price: Int? = null,
    val priceCurrency: String = "",
    @Transient val webcamConsentChecked: Boolean = false,
    @Transient val idConsentChecked: Boolean = false,
) {
    fun isConsentChecked() = webcamConsentChecked && idConsentChecked
}
