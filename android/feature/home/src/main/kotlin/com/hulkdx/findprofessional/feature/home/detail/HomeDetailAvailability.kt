@file:Suppress("FunctionName")

package com.hulkdx.findprofessional.feature.home.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
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
    val lastDay = availability.lengthOfMonth
    val firstDayInt = availability.firstDay

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = outerHorizontalPadding.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        CalendarTop(availability, availabilityMonthMinusOne, availabilityMonthPlusOne)
        CalendarMain(lastDay, firstDayInt, lastDay)
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
    numberOfDaysInMonth: Int,
    firstDay: Int,
    lastDay: Int,
) {
    val perWeek = ceil((numberOfDaysInMonth + firstDay) / 7F).toInt()

    Row {
        for (i in 0..<7) {
            Column(modifier = Modifier.weight(1F)) {
                MonthText(Modifier, i)
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 0.5.dp,
                )
                for (j in 0..<perWeek) {
                    CalendarEachDate(i, j, firstDay, lastDay)
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.CalendarEachDate(
    i: Int,
    j: Int,
    firstDay: Int,
    lastDay: Int,
) {
    val z = (i + 1) + (j * 7) - firstDay
    if (z <= 0 || z > lastDay) {
        Box(modifier = Modifier.aspectRatio(1F))
    } else {
        val calendarColor = MaterialTheme.colorScheme.outlineVariant
        Box(
            modifier = Modifier
                .aspectRatio(1F)
                .padding(4.dp)
                .drawBehind {
                    drawCircle(calendarColor)
                }
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = z.toString(),
                style = body1,
                color = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }
}

@Composable
private fun MonthText(
    modifier: Modifier = Modifier,
    index: Int,
) {
    MonthText(
        modifier,
        requireNotNull(HomeDetailDateFormatter.weekNumberMap[index]),
    )
}

@Composable
private fun MonthText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier.fillMaxWidth(),
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
                    31
                ),
                {},
                {}
            )
        }
    }
}
