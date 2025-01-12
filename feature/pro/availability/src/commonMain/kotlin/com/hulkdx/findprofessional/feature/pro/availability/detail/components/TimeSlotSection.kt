package com.hulkdx.findprofessional.feature.pro.availability.detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.addNewTime
import com.hulkdx.findprofessional.core.ui.commonui.CUTextButton
import com.hulkdx.findprofessional.core.ui.commonui.CuDropDownMenu
import com.hulkdx.findprofessional.core.ui.theme.h3Medium
import com.hulkdx.findprofessional.feature.pro.availability.detail.TimeSlot
import org.jetbrains.compose.resources.stringResource


fun LazyListScope.TimeSlotSection(
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
fun TimeSlotView(
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
fun AddNewTimeButton(onCLick: () -> Unit) {
    CUTextButton(
        modifier = Modifier.padding(horizontal = 24.dp),
        text = stringResource(Res.string.addNewTime),
        onClick = onCLick,
    )
}
