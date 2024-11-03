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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.commonui.CUTextButton
import com.hulkdx.findprofessional.core.commonui.CuDropDownMenu
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.addNewTime
import com.hulkdx.findprofessional.core.resources.editAvailableTime
import com.hulkdx.findprofessional.core.theme.h2
import com.hulkdx.findprofessional.core.theme.h3Medium
import com.hulkdx.findprofessional.core.utils.TimeUtils
import org.jetbrains.compose.resources.stringResource

@Composable
fun EditAvailableTimeScreen() {
    // TODO: move to viewmodel
    val availableTime = remember { (0..<24 * 60 step 30).map { TimeUtils.formattedTime(it) } }

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
        ApplyButton()
        ApplyToAllButton()
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
private fun ApplyButton() {
    CUTextButton(
        modifier = Modifier.padding(horizontal = 24.dp),
        text =  "Apply",
// TODO:
//        text = stringResource(Res.string.apply),
        onClick = {},
    )
}

@Composable
private fun ApplyToAllButton() {
    CUTextButton(
        modifier = Modifier.padding(horizontal = 24.dp),
        text =  "Apply to all",
// TODO:
//        text = stringResource(Res.string.apply),
        onClick = {},
    )
}
