@file:Suppress("FunctionName")

package com.hulkdx.findprofessional.feature.home.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.common.feature.home.detail.availability.AvailabilityData
import com.hulkdx.findprofessional.common.feature.home.detail.availability.HomeDetailAvailabilityUseCase.Companion.weekNumberMap
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body1
import com.hulkdx.findprofessional.core.theme.body1Medium
import com.hulkdx.findprofessional.core.theme.h3Medium
import com.hulkdx.findprofessional.feature.home.detail.HomeScreenDimens.outerHorizontalPadding
import com.hulkdx.findprofessional.resources.MR

internal fun LazyListScope.Availability(
    availability: AvailabilityData,
    availabilityMonthMinusOne: () -> Unit,
    availabilityMonthPlusOne: () -> Unit,
) {
    item { AvailabilityHeader() }
    item { AvailabilityCalendar(availability, availabilityMonthMinusOne, availabilityMonthPlusOne) }
}

@Composable
private fun AvailabilityHeader() {
    Text(
        modifier = Modifier
            .padding(
                top = 16.dp,
                bottom = 8.dp,
                start = outerHorizontalPadding.dp,
            ),
        style = body1Medium,
        text = stringResource(MR.strings.availability.resourceId),
    )
}

@Composable
private fun AvailabilityCalendar(
    availability: AvailabilityData,
    availabilityMonthMinusOne: () -> Unit,
    availabilityMonthPlusOne: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = outerHorizontalPadding.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(bottom = 22.dp)
    ) {
        AvailabilityCalendarTopHeader(
            availability,
            availabilityMonthMinusOne,
            availabilityMonthPlusOne
        )
        AvailabilityCalendarMainContent(availability)
    }
}

@Composable
private fun AvailabilityCalendarTopHeader(
    availability: AvailabilityData,
    availabilityMonthMinusOne: () -> Unit,
    availabilityMonthPlusOne: () -> Unit,
) {
    Row(modifier = Modifier.padding(horizontal = 16.dp)) {
        AvailabilityCalendarTopHeaderButton(
            icon = R.drawable.ic_calendar_left,
            onClick = availabilityMonthMinusOne,
        )
        AvailabilityCalendarTopHeaderMainText(availability.currentMonth)
        AvailabilityCalendarTopHeaderButton(
            icon = R.drawable.ic_calendar_right,
            onClick = availabilityMonthPlusOne
        )
    }
}

@Composable
private fun RowScope.AvailabilityCalendarTopHeaderMainText(currentMonth: String) {
    Text(
        modifier = Modifier
            .weight(1F)
            .align(Alignment.CenterVertically),
        text = currentMonth,
        style = h3Medium,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun AvailabilityCalendarTopHeaderButton(
    icon: Int,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = Modifier.padding(vertical = 6.dp),
        onClick = onClick,
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "",
        )
    }
}

@Composable
private fun AvailabilityCalendarMainContent(
    availability: AvailabilityData,
) {
    Row {
        val daysPerWeek = 7
        for (i in 0..<daysPerWeek) {
            DayColumn(i, availability)
        }
    }
}

@Composable
private fun RowScope.DayColumn(
    dayIndex: Int,
    availability: AvailabilityData,
) {
    Column(modifier = Modifier.weight(1F)) {
        MonthText(dayIndex)
        Divider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = 0.5.dp,
            color = Color(0xFF9D9CAC)
        )
        for (j in 0..<availability.perWeek) {
            CalendarDay(availability, dayIndex, j)
        }
    }
}

@Composable
private fun CalendarDay(
    availability: AvailabilityData,
    i: Int,
    j: Int,
) {
    val lastDay = availability.lengthOfMonth
    val firstDay = availability.firstDay

    val day = (i + 1) + (j * 7) - firstDay
    if (day <= 0 || day > lastDay) {
        EmptyCalendarItem()
    } else {
        CalendarItem(availability, day)
    }
}

@Composable
private fun EmptyCalendarItem() {
    Box(modifier = Modifier.aspectRatio(1F))
}

@Composable
private fun CalendarItem(
    availability: AvailabilityData,
    day: Int,
) {
    val isSelected = availability.isSelectedDay(day)
    if (isSelected) {
        SelectedCalendarItem(day)
    } else {
        NotSelectedCalendarItem(day)
    }
}

@Composable
private fun SelectedCalendarItem(day: Int) {
    val backgroundColor = MaterialTheme.colorScheme.outlineVariant
    Box(
        modifier = Modifier
            .aspectRatio(1F)
            .padding(4.dp)
            .drawBehind {
                drawCircle(backgroundColor)
            }
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = day.toString(),
            style = body1,
            color = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

@Composable
private fun NotSelectedCalendarItem(day: Int) {
    Box(
        modifier = Modifier
            .aspectRatio(1F)
            .padding(4.dp)
            .border(
                width = 1.dp,
                color = Color(0xFF9D9CAC),
                shape = CircleShape,
            )
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = day.toString(),
            style = body1,
            color = Color(0xFF9D9CAC)
        )
    }
}

@Composable
private fun MonthText(
    index: Int,
) {
    val text = requireNotNull(
        weekNumberMap[index]
    )
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        textAlign = TextAlign.Center,
        style = body1Medium,
    )
}

@Preview
@Composable
fun AvailabilityPreview() {
    AppTheme {
        LazyColumn(Modifier.background(MaterialTheme.colorScheme.onPrimary)) {
            Availability(
                AvailabilityData(
                    "January 2022",
                    5,
                    31,
                    1,
                    listOf()
                ),
                {},
                {}
            )
        }
    }
}
