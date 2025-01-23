package com.hulkdx.findprofessional.core.features.pro.model.request

import com.hulkdx.findprofessional.core.utils.PriceUtils
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
    @Transient val priceString: String = "",
    val priceCurrency: String = "",
    @Transient val webcamConsentChecked: Boolean = false,
    @Transient val idConsentChecked: Boolean = false,
) {
    @Suppress("unused") // it will send to the api
    val price = PriceUtils.toPriceNumber(priceString)

    fun isConsentChecked() = webcamConsentChecked && idConsentChecked
}
