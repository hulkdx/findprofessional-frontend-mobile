package com.hulkdx.findprofessional.feature.pro.model.response

import com.hulkdx.findprofessional.feature.pro.model.Booking.Status
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetBookingStatusResponse(
    @SerialName("status") val status: Status,
)
