package com.hulkdx.findprofessional.feature.book.summery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.model.book.SelectedTimes
import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.core.utils.StringOrRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class BookingSummeryViewModel(
    professional: Professional,
    times: SelectedTimes,
    useCase: BookingSummeryUseCase,
) : ViewModel() {
    val uiState = flow { emit(useCase.getUiState(professional, times)) }
        .stateIn(viewModelScope, WhileSubscribed(5_000), null)

    val error = MutableStateFlow<StringOrRes?>(null)

    fun setError(error: StringOrRes?) {
        this.error.value = error
    }
}
