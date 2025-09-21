package com.hulkdx.findprofessional.feature.book.summery.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PayRequest(
    @SerialName("amounts_in_cents") val amountInCents: Long,
    val currency: String,
)