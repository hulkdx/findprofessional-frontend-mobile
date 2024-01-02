package com.hulkdx.findprofessional.feature.review

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.core.utils.getStateFlow
import com.hulkdx.findprofessional.feature.review.ReviewNavigationScreen.Companion.ARG1
import dev.icerock.moko.resources.desc.StringDesc


class ReviewViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val professional = savedStateHandle.getStateFlow<Professional>(ARG1)
    val error = savedStateHandle.getStateFlow<StringDesc?>("error", null)

    fun setError(error: StringDesc?) {
        savedStateHandle["error"] = error
    }
}
