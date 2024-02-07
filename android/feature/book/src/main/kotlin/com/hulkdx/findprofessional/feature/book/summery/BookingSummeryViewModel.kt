package com.hulkdx.findprofessional.feature.book.summery

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hulkdx.findprofessional.common.feature.book.time.SelectedTimes
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryNavigationScreen.Companion.ARG1
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryNavigationScreen.Companion.ARG2
import dev.icerock.moko.resources.desc.StringDesc


class BookingSummeryViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val professional = requireNotNull(savedStateHandle.get<Professional>(ARG1))
    private val times = requireNotNull(savedStateHandle.get<SelectedTimes>(ARG2))

    val error = savedStateHandle.getStateFlow<StringDesc?>("error", null)

    fun setError(error: StringDesc?) {
        savedStateHandle["error"] = error
    }
}
