package com.hulkdx.findprofessional.core.features.pro.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateBookingRequest(
    @SerialName("amount_in_cents") val amountInCents: Long,
    @SerialName("currency") val currency: String,
    @SerialName("idempotency_key") val idempotencyKey: String,
    @SerialName("availabilities") val availabilities: List<CreateBookingRequestAvailability>,
)

@Serializable
data class CreateBookingRequestAvailability(
    val id: String,
)
