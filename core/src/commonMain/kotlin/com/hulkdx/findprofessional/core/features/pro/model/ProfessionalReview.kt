package com.hulkdx.findprofessional.core.features.pro.model

import com.hulkdx.findprofessional.core.features.user.User
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class ProfessionalReview(
    val id: Long,
    val user: User,
    val rate: Int,
    val contentText: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    val formattedDate: String = updatedAt.toLocalDateTime(TimeZone.UTC)
        .let {
            "${it.dayOfMonth} ${it.monthNumber} ${it.year}"
        }
}

