package com.hulkdx.findprofessional.feature.home.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.home.HomeDetailUseCase
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.utils.mapToUi
import com.hulkdx.findprofessional.core.utils.getStateFlow
import com.hulkdx.findprofessional.feature.home.detail.HomeDetailNavigationScreen.Companion.ARG1
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class HomeDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: HomeDetailUseCase,
) : ViewModel() {

    val professional = savedStateHandle.getStateFlow<Professional>(ARG1)
    val error = savedStateHandle.getStateFlow<StringDesc?>("error", null)

    val availability = professional
        .map { it.availability.mapToUi() }
        .flowOn(Dispatchers.Default)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    fun onBookClick() {
    }

    fun setError(error: StringDesc?) {
        savedStateHandle["error"] = error
    }

    fun onReviewShowMoreClicked() {
    }
}
