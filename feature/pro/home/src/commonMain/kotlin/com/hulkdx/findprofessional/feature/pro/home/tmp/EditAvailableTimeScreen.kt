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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
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
import com.hulkdx.findprofessional.core.utils.TimeUtils
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.stringResource

@Composable
fun EditAvailableTimeScreen() {
    // TODO: get it from previous screen
    val selectedDate = LocalDate(2024, 11, 3)

    // TODO: move to viewmodel
    val availableTime = remember { (0..<24 * 60 step 30).map { TimeUtils.formattedTime(it) } }
    val dayOfWeek = selectedDate.dayOfWeek.name
        .lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    val applyAllButtonText = stringResource(Res.string.applyToAll, dayOfWeek)

    val month = selectedDate.month.name
        .lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

    val day = selectedDate.dayOfMonth
    val applyButtonText = stringResource(Res.string.applyToOnly, "$month $day")

    EditAvailableTimeScreen(
        availableTime = availableTime,
        applyButtonText = applyButtonText,
        applyAllButtonText = applyAllButtonText,
    )
}

@Composable
private fun EditAvailableTimeScreen(
    availableTime: List<String>,
    applyButtonText: String,
    applyAllButtonText: String,
) {
    // TODO: use LazyColumn
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .systemBarsPadding()
    ) {
        Title()
        TimeSlot(
            availableTime = availableTime,
            onDeleteClicked = {}
        )
        AddNewTimeButton(onCLick = {})
        ApplyButton(applyButtonText, onCLick = {})
        ApplyToAllButton(applyAllButtonText, onCLick = {})
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
private fun TimeSlot(
    availableTime: List<String>,
    onDeleteClicked: () -> Unit,
) {
    Row(Modifier.padding(start = 24.dp)) {
        CuDropDownMenu(
            modifier = Modifier.weight(1F),
            options = availableTime,
        )
        Text(
            modifier = Modifier.align(CenterVertically)
                .padding(horizontal = 6.dp),
            text = "-",
            style = h3Medium,
        )
        CuDropDownMenu(
            modifier = Modifier.weight(1F),
            options = availableTime,
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
        onClick = {},
    )
}

@Composable
private fun ApplyButton(applyButtonText: String, onCLick: () -> Unit) {
    CUTextButton(
        modifier = Modifier.padding(horizontal = 24.dp),
        text =  applyButtonText,
        onClick = onCLick,
    )
}

@Composable
private fun ApplyToAllButton(applyAllButtonText: String, onCLick: () -> Unit) {
    CUTextButton(
        modifier = Modifier.padding(horizontal = 24.dp),
        text =  applyAllButtonText,
        onClick = onCLick,
    )
}
