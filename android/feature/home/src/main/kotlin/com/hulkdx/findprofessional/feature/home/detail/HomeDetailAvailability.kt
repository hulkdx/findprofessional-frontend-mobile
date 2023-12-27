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
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body1
import com.hulkdx.findprofessional.core.theme.body1Medium
import com.hulkdx.findprofessional.core.theme.h3Medium
import com.hulkdx.findprofessional.feature.home.detail.HomeScreenDimens.outerHorizontalPadding
import com.hulkdx.findprofessional.feature.home.detail.utils.AvailabilityData
import com.hulkdx.findprofessional.feature.home.detail.utils.HomeDetailDateFormatter
import com.hulkdx.findprofessional.resources.MR
import kotlin.math.ceil

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
        CalendarTop(availability, availabilityMonthMinusOne, availabilityMonthPlusOne)
        CalendarMain(availability)
    }
}

@Composable
private fun CalendarTop(
    availability: AvailabilityData,
    availabilityMonthMinusOne: () -> Unit,
    availabilityMonthPlusOne: () -> Unit,
) {
    val currentMonth = availability.calendarDateFormat

    Row(modifier = Modifier.padding(horizontal = 16.dp)) {
        CalendarTopButton(
            icon = R.drawable.ic_calendar_left,
            onClick = availabilityMonthMinusOne,
        )
        CalendarTopMonth(currentMonth)
        CalendarTopButton(
            icon = R.drawable.ic_calendar_right,
            onClick = availabilityMonthPlusOne
        )
    }
}

@Composable
private fun RowScope.CalendarTopMonth(currentMonth: String) {
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
private fun CalendarTopButton(
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
private fun CalendarMain(
    availability: AvailabilityData,
) {
    val lastDay = availability.lengthOfMonth
    val firstDay = availability.firstDay

    val perWeek = ceil((lastDay + firstDay) / 7F).toInt()

    Row {
        for (i in 0..<7) {
            Column(modifier = Modifier.weight(1F)) {
                MonthText(i)
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 0.5.dp,
                    color = Color(0xFF9D9CAC)
                )
                for (j in 0..<perWeek) {
                    CalendarDay(i, j, firstDay, lastDay)
                }
            }
        }
    }
}

@Composable
private fun CalendarDay(
    i: Int,
    j: Int,
    firstDay: Int,
    lastDay: Int,
) {
    val day = (i + 1) + (j * 7) - firstDay
    if (day <= 0 || day > lastDay) {
        EmptyCalendarItem()
    } else {
        NotSelectedCalendarItem(day)
    }
}

@Composable
private fun EmptyCalendarItem() {
    Box(modifier = Modifier.aspectRatio(1F))
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
        HomeDetailDateFormatter.weekNumberMap[index]
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
                    1
                ),
                {},
                {}
            )
        }
    }
}
