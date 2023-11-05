package com.hulkdx.findprofessional.common.feature.home.model

import com.hulkdx.findprofessional.common.utils.CommonParcelable
import com.hulkdx.findprofessional.common.utils.CommonParcelize
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

@CommonParcelize
@Serializable
data class ProfessionalAvailability(
    val date: LocalDate,
    val from: LocalTime,
    val to: LocalTime,
) : CommonParcelable

fun map(a: List<ProfessionalAvailability>): List<String> {
    TODO()
}
