package com.hulkdx.findprofessional.feature.home.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.home.detail.availability.HomeDetailAvailabilityUseCase
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import com.hulkdx.findprofessional.core.utils.getStateFlow
import com.hulkdx.findprofessional.feature.home.detail.HomeDetailNavigationScreen.Companion.ARG1
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.stateIn


class HomeDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val navigator: Navigator,
    private val availabilityUseCase: HomeDetailAvailabilityUseCase,
) : ViewModel() {

    val professional = savedStateHandle.getStateFlow<Professional>(ARG1)
    val error = savedStateHandle.getStateFlow<StringDesc?>("error", null)

    val availability = availabilityUseCase.getAvailabilityData(professional)
        .stateIn(viewModelScope, WhileSubscribed(5_000), null)

    fun onLikeClick() {
    }

    fun onChatClick() {
    }

    fun onBookClick() {
        navigator.navigate(NavigationScreen.Book(professional.value))
    }

    fun onShowMoreReviewClick() {
        navigator.navigate(NavigationScreen.Review(professional.value))
    }

    fun setError(error: StringDesc?) {
        savedStateHandle["error"] = error
    }

    fun availabilityMonthMinusOne() {
        availabilityUseCase.monthMinusOne()
    }

    fun availabilityMonthPlusOne() {
        availabilityUseCase.monthPlusOne()
    }
}
