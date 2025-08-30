package com.hulkdx.findprofessional.feature.book.summery.api

import kotlinx.serialization.Serializable

@Serializable
data class PayResponse(
    val paymentIntent: String,
    val ephemeralKey: String,
    val customer: String,
    val publishableKey: String,
)