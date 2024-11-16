package com.hulkdx.findprofessional.feature.pro.availability.time

import androidx.lifecycle.ViewModel
import com.hulkdx.findprofessional.core.utils.TimeUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate

class EditAvailableTimeViewModel(
    selectedDate: LocalDate,
    selectedTimeSlot: List<TimeSlot>,
) : ViewModel() {
    val availableTime: List<String> = (0..<24 * 60 step 30).map { TimeUtils.formattedTime(it) }

    val dayOfWeek = selectedDate.dayOfWeek.name
        .lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

    val applyButtonText = run {
        val month = selectedDate.month.name
            .lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        val day = selectedDate.dayOfMonth
        "$month $day"
    }

    private val _timeSlots = MutableStateFlow(selectedTimeSlot)
    val timeSlots = _timeSlots.asStateFlow()

    fun onDeleteClicked(index: Int) {
        _timeSlots.update { it.toMutableList().apply { removeAt(index) } }
    }

    fun onFromSelected(index: Int, time: String) {
        _timeSlots.update {
            it.toMutableList().apply {
                this[index] = this[index].copy(from = time)
            }
        }
    }

    fun onToSelected(index: Int, time: String) {
        _timeSlots.update {
            it.toMutableList().apply {
                this[index] = this[index].copy(to = time)
            }
        }
    }

    fun onAddNewTimeSlotClicked() {
        _timeSlots.update { it + TimeSlot("00:00", "00:00") }
    }

    fun onApplyClicked() {
        // TODO:
    }

    fun onApplyToAllClicked() {
        // TODO:
    }
}
