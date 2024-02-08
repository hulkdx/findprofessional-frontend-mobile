package com.hulkdx.findprofessional.feature.book.summery

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.book.time.SelectedTimes
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryNavigationScreen.Companion.ARG1
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryNavigationScreen.Companion.ARG2
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class BookingSummeryViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val professional = requireNotNull(savedStateHandle.get<Professional>(ARG1))
    private val times = requireNotNull(savedStateHandle.get<SelectedTimes>(ARG2))

    val uiState = flow { emit(BookingSummeryUiState(listOf(), "", "")) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            BookingSummeryUiState(listOf(), "", "")
        )

    val error = savedStateHandle.getStateFlow<StringDesc?>("error", null)

    fun setError(error: StringDesc?) {
        savedStateHandle["error"] = error
    }
}
