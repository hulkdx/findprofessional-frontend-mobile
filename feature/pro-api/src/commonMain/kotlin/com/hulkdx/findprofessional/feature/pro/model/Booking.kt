package com.hulkdx.findprofessional.feature.pro.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class Booking(
    val id: Long,
    val status: Status,
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

    @Serializable
    enum class Status {
        @SerialName("pending")
        PENDING,

        @SerialName("confirmed")
        CONFIRMED,

        @SerialName("failed")
        FAILED,

        @SerialName("completed")
        COMPLETED,
        ;
    }
}
