package com.hulkdx.findprofessional.feature.pro.home.tmp

import androidx.lifecycle.ViewModel
import com.hulkdx.findprofessional.core.utils.TimeUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.LocalDate

class EditAvailableTimeViewModel(
    selectedDate: LocalDate,
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

    private val _timeSlots = MutableStateFlow<List<TimeSlot>>(emptyList())
    val timeSlots = _timeSlots.asStateFlow()

    fun onDeleteClicked(index: Int) {
    }

    fun onFromSelected(index: Int) {
    }

    fun onToSelected(index: Int) {
    }

    fun onAddNewTimeSlotClicked() {
    }
}
