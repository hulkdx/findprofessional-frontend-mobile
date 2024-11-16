package com.hulkdx.findprofessional.feature.pro.availability

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hulkdx.findprofessional.core.utils.lengthOfMonth
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime


@Composable
fun ProAvailabilityCalendarView(onDateSelected: (LocalDate) -> Unit) {
    var instant by remember { mutableStateOf(Clock.System.now()) }
    val localDateTime = remember(instant) { instant.toLocalDateTime(TimeZone.currentSystemDefault()) }

    Column(modifier = Modifier.padding(top = 100.dp)) {
        MonthPicker(
            month = localDateTime.month.name,
            year = localDateTime.year,
            onPreviousMonth = {
                instant = instant.minus(1, DateTimeUnit.MONTH, TimeZone.currentSystemDefault())
            },
            onNextMonth = {
                instant = instant.plus(1, DateTimeUnit.MONTH, TimeZone.currentSystemDefault())
            },
        )

        DaysOfTheWeekHeader()

        // Calendar Days
        CalendarDays(localDateTime, onDateSelected)
    }
}

@Composable
private fun MonthPicker(
    month: String,
    year: Int,
    onNextMonth: () -> Unit,
    onPreviousMonth: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPreviousMonth) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous Month")
        }
        Text(
            text = "$month $year",
            style = LocalTextStyle.current.copy(fontSize = 20.sp),
            textAlign = TextAlign.Center
        )
        IconButton(onClick = onNextMonth) {
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next Month")
        }
    }
}

@Composable
private fun DaysOfTheWeekHeader() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        listOf("S", "M", "T", "W", "T", "F", "S").forEach { day ->
            Text(
                text = day,
                style = LocalTextStyle.current.copy(fontSize = 16.sp, color = Color.Gray),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
private fun CalendarDays(
    localDateTime: LocalDateTime,
    onDateSelected: (LocalDate) -> Unit
) {
    val daysInMonth = localDateTime.lengthOfMonth()

    val firstDayOfMonth =
        (LocalDate(localDateTime.year, localDateTime.month, 1).dayOfWeek.ordinal % 7) + 1
    val dates = (1..daysInMonth).map { LocalDate(localDateTime.year, localDateTime.month, it) }

    val emptyStartDays = List(firstDayOfMonth) { null }
    val emptyEndDays = List(7 - (firstDayOfMonth + dates.size) % 7) { null }

    val days = emptyStartDays + dates + emptyEndDays

    Column {
        days.chunked(7).forEach { week ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                week.forEach { date ->
                    Day(date, onDateSelected)
                }
            }
        }
    }
}

@Composable
private fun Day(
    date: LocalDate?,
    onDateSelected: (LocalDate) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(40.dp)
            .background(
                Color.Transparent,
                shape = MaterialTheme.shapes.small
            )
            .clickable {
                date?.let { onDateSelected(it) }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date?.dayOfMonth?.toString() ?: "",
            textAlign = TextAlign.Center,
            style = LocalTextStyle.current.copy(fontSize = 16.sp)
        )
    }
}
