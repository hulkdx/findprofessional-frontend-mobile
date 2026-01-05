package com.hulkdx.findprofessional.feature.pro.model

import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class Booking(
    val id: Long,
    val status: String,
    val scheduledStartAt: Instant,
    val scheduledEndAt: Instant,
    val totalAmountCents: Long,
    val currency: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val professional: BookingParty,
    val user: BookingParty,
    val session: SessionInfo,
) {
    @Serializable
    data class BookingParty(
        val id: Long,
        val firstName: String,
        val lastName: String,
        val email: String? = null,
    )

    @Serializable
    data class SessionInfo(
        val sessionPlatform: String? = null,
        val sessionLink: String? = null,
    )
}
