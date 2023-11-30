package com.hulkdx.findprofessional.common.feature.home.model

import com.hulkdx.findprofessional.common.feature.authentication.model.User
import com.hulkdx.findprofessional.common.utils.CommonParcelable
import com.hulkdx.findprofessional.common.utils.CommonParcelize
import com.hulkdx.findprofessional.common.utils.CommonTypeParceler
import com.hulkdx.findprofessional.common.utils.InstantParceler
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@CommonParcelize
@Serializable
@CommonTypeParceler<Instant, InstantParceler>
data class ProfessionalReview(
    val id: Long,
    val user: User,
    val rate: Int,
    val contentText: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
): CommonParcelable

