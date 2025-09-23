package com.hulkdx.findprofessional.core.features.pro.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateBookingResponse(
    @SerialName("payment_intent") val paymentIntent: String,
    @SerialName("ephemeral_key") val ephemeralKey: String,
    @SerialName("customer") val customer: String,
    @SerialName("publishable_key") val publishableKey: String,
)
