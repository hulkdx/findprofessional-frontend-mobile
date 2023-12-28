package com.hulkdx.findprofessional.feature.home.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.home.detail.availability.HomeDetailAvailabilityUseCase
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.core.utils.getStateFlow
import com.hulkdx.findprofessional.feature.home.detail.HomeDetailNavigationScreen.Companion.ARG1
import com.hulkdx.findprofessional.feature.home.detail.utils.AvailabilityData
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import java.time.LocalDate


class HomeDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val availabilityUseCase: HomeDetailAvailabilityUseCase,
) : ViewModel() {

    private val date = MutableStateFlow(LocalDate.now())

    val professional = savedStateHandle.getStateFlow<Professional>(ARG1)
    val error = savedStateHandle.getStateFlow<StringDesc?>("error", null)

    val availability = professional
        .combine(date, ::Pair)
        .map { (professional, date) -> createAvailabilityData(professional, date) }
        .distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            initialValue = createAvailabilityData(professional.value, date.value),
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
        date.value = date.value.minusMonths(1)
    }

    fun availabilityMonthPlusOne() {
        date.value = date.value.plusMonths(1)
    }

    private fun createAvailabilityData(
        professional: Professional,
        now: LocalDate = LocalDate.now(),
    ) = AvailabilityData(
        currentMonth = availabilityUseCase.currentMonth(now.toKotlinLocalDate()),
        firstDay = availabilityUseCase.firstDayInt(now.toKotlinLocalDate()),
        lengthOfMonth = availabilityUseCase.lengthOfMonth(now.toKotlinLocalDate()),
        now = now.toEpochDay(),
        professionalAvailabilityDates = professional.availability.map { it.date.toJavaLocalDate() },
    )
}
