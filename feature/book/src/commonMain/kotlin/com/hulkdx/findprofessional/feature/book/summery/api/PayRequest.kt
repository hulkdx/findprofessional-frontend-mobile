package com.hulkdx.findprofessional.feature.book.summery.api

import kotlinx.serialization.Serializable

@Serializable
data class PayRequest(
    val amountsInCents: Long,
    val currency: String,
)