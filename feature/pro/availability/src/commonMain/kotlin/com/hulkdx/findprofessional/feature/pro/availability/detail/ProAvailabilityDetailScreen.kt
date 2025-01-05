@file:Suppress("FunctionName")

package com.hulkdx.findprofessional.feature.pro.availability.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.addNewTime
import com.hulkdx.findprofessional.core.resources.applyToAll
import com.hulkdx.findprofessional.core.resources.applyToOnly
import com.hulkdx.findprofessional.core.resources.editAvailableTime
import com.hulkdx.findprofessional.core.ui.commonui.CUTextButton
import com.hulkdx.findprofessional.core.ui.commonui.CuDropDownMenu
import com.hulkdx.findprofessional.core.ui.theme.h2
import com.hulkdx.findprofessional.core.ui.theme.h3Medium
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ProAvailabilityDetailScreen(
    selectedDate: LocalDate,
    selectedTimeSlot: List<TimeSlot> = emptyList(), // TODO: get it from previous screen
    viewModel: ProAvailabilityDetailViewModel = koinViewModel {
        parametersOf(
            selectedDate,
            selectedTimeSlot
        )
    }
) {
    val timeSlots by viewModel.timeSlots.collectAsState()

    ProAvailabilityDetailScreen(
        availableTime = viewModel.availableTime,
        dayOfWeek = viewModel.dayOfWeek,
        applyButtonText = viewModel.applyButtonText,
        timeSlots = timeSlots,
        onDeleteClicked = viewModel::onDeleteClicked,
        onFromSelected = viewModel::onFromSelected,
        onToSelected = viewModel::onToSelected,
        onAddNewTimeSlotClicked = viewModel::onAddNewTimeSlotClicked,
        onApplyClicked = viewModel::onApplyClicked,
        onApplyToAllClicked = viewModel::onApplyToAllClicked,
    )
}

@Composable
private fun ProAvailabilityDetailScreen(
    timeSlots: List<TimeSlot>,
    availableTime: List<String>,
    onDeleteClicked: (Int) -> Unit,
    onFromSelected: (Int, String) -> Unit,
    onToSelected: (Int, String) -> Unit,
    onAddNewTimeSlotClicked: () -> Unit,
    applyButtonText: String,
    dayOfWeek: String,
    onApplyClicked: () -> Unit,
    onApplyToAllClicked: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .testTag("ProAvailabilityDetailScreen")
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .systemBarsPadding()
    ) {
        item { Title() }
        TimeSlotSection(
            timeSlots,
            availableTime,
            onDeleteClicked,
            onFromSelected,
            onToSelected,
            onAddNewTimeSlotClicked,
        )
        item { ApplyButton(applyButtonText, onApplyClicked) }
        item { ApplyToAllButton(dayOfWeek, onApplyToAllClicked) }
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

private fun LazyListScope.TimeSlotSection(
    timeSlots: List<TimeSlot>,
    availableTime: List<String>,
    onDeleteClicked: (Int) -> Unit,
    onFromSelected: (Int, String) -> Unit,
    onToSelected: (Int, String) -> Unit,
    onAddNewTimeSlotClicked: () -> Unit,
) {
    itemsIndexed(timeSlots) { index, timeSlot ->
        TimeSlotView(
            timeSlot = timeSlot,
            availableTime = availableTime,
            onDeleteClicked = { onDeleteClicked(index) },
            onFromSelected = { onFromSelected(index, it) },
            onToSelected = { onToSelected(index, it) },
        )
    }

    item { AddNewTimeButton(onCLick = onAddNewTimeSlotClicked) }
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
