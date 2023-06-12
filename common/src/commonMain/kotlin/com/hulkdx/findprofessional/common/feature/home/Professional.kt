package com.hulkdx.findprofessional.common.feature.home

import com.hulkdx.findprofessional.common.utils.CommonParcelable
import com.hulkdx.findprofessional.common.utils.CommonParcelize
import kotlinx.serialization.Serializable

@CommonParcelize
@Serializable
data class Professional(
    val fullName: String,
    val coachType: String,
    val price: String,
    val description: String,
    val imageUrl: String,
    val rating: String,
) : CommonParcelable
