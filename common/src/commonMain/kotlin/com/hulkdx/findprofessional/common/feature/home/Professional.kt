package com.hulkdx.findprofessional.common.feature.home

import kotlinx.serialization.Serializable

@Serializable
data class Professional(
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String,
)
