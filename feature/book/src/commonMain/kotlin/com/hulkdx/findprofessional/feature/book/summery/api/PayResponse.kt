package com.hulkdx.findprofessional.feature.book.summery.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PayResponse(
    @SerialName("payment_intent") val paymentIntent: String,
    @SerialName("ephemeral_key") val ephemeralKey: String,
    @SerialName("customer") val customer: String,
    @SerialName("publishable_key") val publishableKey: String,
)