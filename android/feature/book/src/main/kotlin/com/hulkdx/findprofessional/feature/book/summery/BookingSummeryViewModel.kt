package com.hulkdx.findprofessional.feature.book.summery

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dev.icerock.moko.resources.desc.StringDesc


class BookingSummeryViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val error = savedStateHandle.getStateFlow<StringDesc?>("error", null)

    fun setError(error: StringDesc?) {
        savedStateHandle["error"] = error
    }
}
