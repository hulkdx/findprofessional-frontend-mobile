package com.hulkdx.findprofessional.feature.pro.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.features.pro.usecase.GetAvailabilityUseCase
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.core.utils.generalError
import com.hulkdx.findprofessional.feature.pro.schedule.model.ScheduleUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ProScheduleViewModel(
    getScheduleUseCase: GetAvailabilityUseCase,
) : ViewModel() {
    val state = flow { emit(getScheduleUseCase.execute()) }
        .catch { setError(it.generalError()) }
        .map {
            ScheduleUiState()
        }
        .stateIn(viewModelScope, WhileSubscribed(5_000), ScheduleUiState())

    private val _error = MutableStateFlow<StringOrRes?>(null)
    val error = _error.asStateFlow()

    fun setError(error: StringOrRes?) = _error.update { error }
}
