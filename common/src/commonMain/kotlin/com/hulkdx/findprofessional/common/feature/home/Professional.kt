package com.hulkdx.findprofessional.common.feature.home

import kotlinx.serialization.Serializable

@Serializable
data class Professional(
    val title: String,
    val type: String,
    val price: String,
    val imageUrl: String,
    val star: String,
)
