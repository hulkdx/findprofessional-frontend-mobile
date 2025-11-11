package com.hulkdx.findprofessional.core.features.book

import kotlinx.serialization.Serializable

@Serializable
data class BookingSummeryTime(
    val duration: String,
    val date: String,
    val day: String,
)
