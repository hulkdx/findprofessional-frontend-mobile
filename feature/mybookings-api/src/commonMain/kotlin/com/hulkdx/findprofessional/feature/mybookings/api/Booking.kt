package com.hulkdx.findprofessional.feature.mybookings.api

import kotlinx.serialization.Serializable
import kotlin.time.Instant

typealias MyBookingsResponse = List<Booking>

@Serializable
data class Party(
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
data class Booking(
    val id: Long,
    val status: String,
    val scheduledStartAt: Instant,
    val scheduledEndAt: Instant,
    val totalAmountCents: Long,
    val currency: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val professional: Party,
    val user: Party,
    val session: SessionInfo,
)
