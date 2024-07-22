package com.hulkdx.findprofessional.core.model.book

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class SelectedTimes(
    val items: Map<LocalDate, Set<Int>> = mapOf(),
)
