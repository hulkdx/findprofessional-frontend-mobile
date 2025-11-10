package com.hulkdx.findprofessional.feature.pro.availability.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.core.utils.TimeUtils
import com.hulkdx.findprofessional.feature.pro.availability.detail.usecase.UpdateAvailabilityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

class ProAvailabilityDetailViewModel(
    private val navigator: Navigator,
    private val updateAvailabilityUseCase: UpdateAvailabilityUseCase,
    private val selectedDate: LocalDate,
    selectedTimeSlot: List<TimeSlot>,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState.create(selectedDate, selectedTimeSlot))
    val uiState = _uiState.asStateFlow()

    private val _error = MutableStateFlow<StringOrRes?>(null)
    val error = _error.asStateFlow()

    fun onDeleteClicked(index: Int) {
        val timeSlots = uiState.value.timeSlots.toMutableList()
        timeSlots.removeAt(index)
        setTimeSlots(timeSlots)
    }

    fun onFromSelected(index: Int, time: String) {
        val localTime = LocalTime.parse(time)
        val timeSlots = uiState.value.timeSlots.toMutableList()
        timeSlots[index] = timeSlots[index].copy(from = localTime)
        setTimeSlots(timeSlots)
    }

    fun onToSelected(index: Int, time: String) {
        val localTime = LocalTime.parse(time)
        val timeSlots = uiState.value.timeSlots.toMutableList()
        timeSlots[index] = timeSlots[index].copy(to = localTime)
        setTimeSlots(timeSlots)
    }

    fun onAddNewTimeSlotClicked() {
        val timeSlots = uiState.value.timeSlots.toMutableList()
        setTimeSlots(
            timeSlots + TimeSlot(
                from = LocalTime(hour = 0, minute = 0),
                to = LocalTime(hour = 0, minute = 0),
            )
        )
    }

    fun onApplyClicked() {
        viewModelScope.launch {
            val err = updateAvailabilityUseCase.execute(
                timeSlots = uiState.value.timeSlots,
                date = selectedDate,
            )
            if (err == null) {
                navigator.goBack()
            } else {
                setError(err)
            }
        }
    }

    fun onApplyToAllClicked() {
        // TODO:
        viewModelScope.launch {
        }
    }

    private fun setTimeSlots(timeSlots: List<TimeSlot>) {
        _uiState.update { it.copy(timeSlots = timeSlots) }
    }

    fun setError(error: StringOrRes?) = _error.update { error }

    data class UiState(
        val timeSlots: List<TimeSlot>,
        val availableTime: List<String>,
        val dayOfWeek: String,
        val applyButtonText: String,
        val currentDate: String,
    ) {
        companion object {
            fun create(
                selectedDate: LocalDate,
                selectedTimeSlot: List<TimeSlot>,
            ) = UiState(
                timeSlots = selectedTimeSlot,
                availableTime = (0..<24 * 60 step 30).map { TimeUtils.formattedTime(it) },
                dayOfWeek = selectedDate.dayOfWeek.name
                    .lowercase()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                applyButtonText = run {
                    val month = selectedDate.month.name
                        .lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                    val day = selectedDate.dayOfMonth
                    "$day $month"
                },
                currentDate = run {
                    val month = selectedDate.month.name
                        .lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                    val day = selectedDate.dayOfMonth
                    val year = selectedDate.year
                    "$day $month $year"
                },
            )
        }
    }
}
