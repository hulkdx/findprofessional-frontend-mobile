package com.hulkdx.findprofessional.feature.pro.availability

import androidx.lifecycle.ViewModel
import com.hulkdx.findprofessional.core.utils.StringOrRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate

class ProAvailabilityViewModel: ViewModel() {
    private val _error = MutableStateFlow<StringOrRes?>(null)
    val error = _error.asStateFlow()

    fun onErrorDismissed() = _error.update { null }

    fun onDateClicked(date: LocalDate) {
    }

    fun isAvailableDay(day: Int): Boolean {
        return true
    }
}
