package com.hulkdx.findprofessional.feature.pro.home.tmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.commonui.CUTextButton
import com.hulkdx.findprofessional.core.commonui.CuDropDownMenu
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.addNewTime
import com.hulkdx.findprofessional.core.resources.applyToAll
import com.hulkdx.findprofessional.core.resources.applyToOnly
import com.hulkdx.findprofessional.core.resources.editAvailableTime
import com.hulkdx.findprofessional.core.theme.h2
import com.hulkdx.findprofessional.core.theme.h3Medium
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.stringResource
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun EditAvailableTimeScreen(
    selectedDate: LocalDate = LocalDate(2024, 11, 3),
    viewModel: EditAvailableTimeViewModel = koinViewModel { parametersOf(selectedDate) }
) {
    val timeSlots by viewModel.timeSlots.collectAsState()

    EditAvailableTimeScreen(
        availableTime = viewModel.availableTime,
        dayOfWeek = viewModel.dayOfWeek,
        applyButtonText = viewModel.applyButtonText,
        timeSlots = timeSlots,
        onDeleteClicked = viewModel::onDeleteClicked,
        onFromSelected = viewModel::onFromSelected,
        onToSelected = viewModel::onToSelected,
        onAddNewTimeSlotClicked = viewModel::onAddNewTimeSlotClicked,
    )
}

@Composable
private fun EditAvailableTimeScreen(
    timeSlots: List<TimeSlot>,
    availableTime: List<String>,
    onDeleteClicked: (Int) -> Unit,
    onFromSelected: (Int, String) -> Unit,
    onToSelected: (Int, String) -> Unit,
    onAddNewTimeSlotClicked: () -> Unit,
    applyButtonText: String,
    dayOfWeek: String,
) {
    // TODO: use LazyColumn
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .systemBarsPadding()
    ) {
        Title()
        TimeSlotSection(
            timeSlots,
            availableTime,
            onDeleteClicked,
            onFromSelected,
            onToSelected,
            onAddNewTimeSlotClicked,
        )
        ApplyButton(applyButtonText, onCLick = {})
        ApplyToAllButton(dayOfWeek, onCLick = {})
    }
}

@Composable
private fun Title() {
    Text(
        modifier = Modifier
            .padding(vertical = 32.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = stringResource(Res.string.editAvailableTime),
        style = h2,
    )
}

@Composable
private fun TimeSlotSection(
    timeSlots: List<TimeSlot>,
    availableTime: List<String>,
    onDeleteClicked: (Int) -> Unit,
    onFromSelected: (Int, String) -> Unit,
    onToSelected: (Int, String) -> Unit,
    onAddNewTimeSlotClicked: () -> Unit,
) {
    for ((index, timeSlot) in timeSlots.withIndex()) {
        TimeSlotView(
            timeSlot = timeSlot,
            availableTime = availableTime,
            onDeleteClicked = { onDeleteClicked(index) },
            onFromSelected = { onFromSelected(index, it) },
            onToSelected = { onToSelected(index, it) },
        )
    }

    AddNewTimeButton(onCLick = onAddNewTimeSlotClicked)
}

@Composable
private fun TimeSlotView(
    timeSlot: TimeSlot,
    availableTime: List<String>,
    onDeleteClicked: () -> Unit,
    onFromSelected: (String) -> Unit,
    onToSelected: (String) -> Unit,
) {
    Row(Modifier.padding(start = 24.dp, bottom = 8.dp)) {
        CuDropDownMenu(
            initialValue = timeSlot.from,
            modifier = Modifier.weight(1F),
            options = availableTime,
            onValueChanged = onFromSelected,
        )
        Text(
            modifier = Modifier.align(CenterVertically)
                .padding(horizontal = 6.dp),
            text = "-",
            style = h3Medium,
        )
        CuDropDownMenu(
            initialValue = timeSlot.to,
            modifier = Modifier.weight(1F),
            options = availableTime,
            onValueChanged = onToSelected,
        )
        IconButton(onClick = onDeleteClicked) {
            Icon(
                imageVector = Icons.Default.Delete,
                tint = MaterialTheme.colorScheme.errorContainer,
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun AddNewTimeButton(onCLick: () -> Unit) {
    CUTextButton(
        modifier = Modifier.padding(horizontal = 24.dp),
        text = stringResource(Res.string.addNewTime),
        onClick = onCLick,
    )
}

@Composable
private fun ApplyButton(applyButtonText: String, onCLick: () -> Unit) {
    CUTextButton(
        modifier = Modifier.padding(horizontal = 24.dp),
        text = stringResource(Res.string.applyToOnly, applyButtonText),
        onClick = onCLick,
    )
}

@Composable
private fun ApplyToAllButton(dayOfWeek: String, onCLick: () -> Unit) {
    CUTextButton(
        modifier = Modifier.padding(horizontal = 24.dp),
        text = stringResource(Res.string.applyToAll, dayOfWeek),
        onClick = onCLick,
    )
}
