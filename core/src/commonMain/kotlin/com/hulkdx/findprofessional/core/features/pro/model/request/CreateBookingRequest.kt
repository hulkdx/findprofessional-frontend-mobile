package com.hulkdx.findprofessional.core.features.pro.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateBookingRequest(
    @SerialName("amounts_in_cents") val amountInCents: Long,
    @SerialName("currency") val currency: String,
)
