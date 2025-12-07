package com.hulkdx.findprofessional.core.features.pro.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetBookingStatusResponse(
    @SerialName("status") val status: Status,
) {
    @Serializable
    enum class Status {
        @SerialName("pending") PENDING,
        @SerialName("succeeded") SUCCEEDED,
        @SerialName("failed") FAILED,
    }
}
