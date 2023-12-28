package com.hulkdx.findprofessional.feature.home.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.home.detail.availability.AvailabilityData
import com.hulkdx.findprofessional.common.feature.home.detail.availability.HomeDetailAvailabilityUseCase
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.core.utils.getStateFlow
import com.hulkdx.findprofessional.feature.home.detail.HomeDetailNavigationScreen.Companion.ARG1
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn


class HomeDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val availabilityUseCase: HomeDetailAvailabilityUseCase,
) : ViewModel() {

    private val date = MutableStateFlow(Clock.System.todayIn(TimeZone.UTC))

    val professional = savedStateHandle.getStateFlow<Professional>(ARG1)
    val error = savedStateHandle.getStateFlow<StringDesc?>("error", null)

    val availability = availabilityUseCase.getAvailabilityData(professional, date)
        .stateIn(
            scope = viewModelScope,
            initialValue = AvailabilityData(
                currentMonth = "",
                firstDay = 0,
                lengthOfMonth = 0,
                now = 0,
                professionalAvailabilityDates = listOf()
            ),
            started = WhileSubscribed(5_000),
        )

    fun onBookClick() {
        // TODO:
    }

    fun onReviewShowMoreClicked() {
        // TODO:
    }

    fun setError(error: StringDesc?) {
        savedStateHandle["error"] = error
    }

    fun availabilityMonthMinusOne() {
        date.value = date.value.minus(1, DateTimeUnit.MONTH)
    }

    fun availabilityMonthPlusOne() {
        date.value = date.value.plus(1, DateTimeUnit.MONTH)
    }
}
