package com.hulkdx.findprofessional.feature.home.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hulkdx.findprofessional.common.feature.home.HomeDetailUseCase
import com.hulkdx.findprofessional.common.feature.home.Professional
import com.hulkdx.findprofessional.feature.home.detail.HomeDetailNavigationScreen.Companion.ARG1
import dev.icerock.moko.resources.desc.StringDesc


class HomeDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: HomeDetailUseCase,
) : ViewModel() {

    val professional = requireNotNull(savedStateHandle.get<Professional>(ARG1))
    val error = savedStateHandle.getStateFlow<StringDesc?>("error", null)

    fun onBookClick() {
    }

    fun setError(error: StringDesc?) {
        savedStateHandle["error"] = error
    }
}
