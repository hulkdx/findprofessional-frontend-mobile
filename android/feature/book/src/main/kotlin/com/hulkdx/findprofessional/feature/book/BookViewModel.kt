package com.hulkdx.findprofessional.feature.book

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.book.BookUiState.BookingTime
import com.hulkdx.findprofessional.common.feature.book.BookUseCase
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.feature.book.BookNavigationScreen.Companion.ARG1
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn


class BookViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: BookUseCase,
) : ViewModel() {
    private val professional = requireNotNull(savedStateHandle.get<Professional>(ARG1))

    val error = savedStateHandle.getStateFlow<StringDesc?>("error", null)

    val uiState = useCase.getUiState(professional)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    fun setError(error: StringDesc?) {
        savedStateHandle["error"] = error
    }

    fun dayMinusOne() {
        useCase.dayMinusOne()
    }

    fun dayPlusOne() {
        useCase.dayPlusOne()
    }

    fun onTimeClicked(item: BookingTime) {
        useCase.onTimeClicked(item)
    }
}
