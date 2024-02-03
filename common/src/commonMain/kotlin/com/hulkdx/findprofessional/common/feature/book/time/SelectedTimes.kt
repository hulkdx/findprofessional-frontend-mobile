package com.hulkdx.findprofessional.common.feature.book.time

import kotlinx.datetime.LocalDate

data class SelectedTimes(
    val items: Map<LocalDate, Set<Int>> = mapOf(),
)
