package com.hulkdx.findprofessional.feature.book.time

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.features.pro.Professional
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.feature.book.time.BookingTimeUiState.BookingTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


class BookingTimeViewModel(
    private val professional: Professional,
    private val useCase: BookingTimeUseCase,
) : ViewModel() {

    private val _error = MutableStateFlow<StringOrRes?>(null)
    val error = _error.asStateFlow()

    val uiState = useCase.getUiState(professional)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    fun dayMinusOne() {
        useCase.dayMinusOne()
    }

    fun dayPlusOne() {
        useCase.dayPlusOne()
    }

    fun onTimeClicked(item: BookingTime) {
        useCase.onTimeClicked(item)
    }

    fun onContinueClicked() {
        useCase.onContinueClicked(professional)
    }

    fun setError(error: StringOrRes?) = _error.update { error }
}
