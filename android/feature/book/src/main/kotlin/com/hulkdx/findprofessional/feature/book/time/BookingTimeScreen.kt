package com.hulkdx.findprofessional.feature.book.time

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.common.feature.book.time.BookingTimeUiState
import com.hulkdx.findprofessional.common.feature.book.time.BookingTimeUiState.BookingTime
import com.hulkdx.findprofessional.common.feature.book.time.BookingTimeUiState.BookingTime.Type.Available
import com.hulkdx.findprofessional.common.feature.book.time.BookingTimeUiState.BookingTime.Type.Selected
import com.hulkdx.findprofessional.common.feature.book.time.BookingTimeUiState.BookingTime.Type.UnAvailable
import com.hulkdx.findprofessional.common.utils.now
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.core.commonui.CUFilledButton
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body2SemiBold
import com.hulkdx.findprofessional.core.theme.body3Medium
import com.hulkdx.findprofessional.core.theme.h2Medium
import com.hulkdx.findprofessional.resources.MR
import dev.icerock.moko.resources.compose.localized
import kotlinx.datetime.LocalDate
import org.koin.androidx.compose.koinViewModel


@Composable
fun BookingTimeScreen(viewModel: BookingTimeViewModel = koinViewModel()) {
    val error by viewModel.error.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BookingTimeScreen(
        uiState = uiState ?: return,
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) },
        dayMinusOne = viewModel::dayMinusOne,
        dayPlusOne = viewModel::dayPlusOne,
        onTimeClicked = viewModel::onTimeClicked,
        onContinueClicked = viewModel::onContinueClicked,
    )
}

@Composable
fun BookingTimeScreen(
    uiState: BookingTimeUiState,
    dayMinusOne: () -> Unit,
    dayPlusOne: () -> Unit,
    onTimeClicked: (BookingTime) -> Unit,
    error: String?,
    onErrorDismissed: () -> Unit,
    onContinueClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .systemBarsPadding()
            .testTag("BookingTimeScreen")
    ) {
        LazyColumn(Modifier.testTag("BookingTimeScreen.LazyColumn")) {
            item { Header() }
            item { DayHeader(uiState.currentDate, dayPlusOne, dayMinusOne) }
            items(uiState.times) { (first, second) ->
                TimeItem(first, second, onTimeClicked)
            }
            item { Spacer(modifier = Modifier.height(90.dp)) }
        }
        ContinueButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = onContinueClicked,
        )
        CUSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            message = error,
            onDismiss = onErrorDismissed,
        )
    }
}

@Composable
private fun Header() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 45.dp, bottom = 33.dp),
        text = stringResource(MR.strings.selectOneOrMoreItems.resourceId),
        style = h2Medium,
        textAlign = Center,
    )
}

@Composable
private fun DayHeader(
    currentDate: String,
    dayPlusOne: () -> Unit,
    dayMinusOne: () -> Unit,
) {
    Row(
        Modifier
            .padding(horizontal = 16.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp
                )
            )
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(bottom = 12.dp)
    ) {
        DayHeaderButton(
            icon = R.drawable.ic_calendar_left,
            onClick = dayMinusOne,
        )
        DayHeaderText(currentDate)
        DayHeaderButton(
            icon = R.drawable.ic_calendar_right,
            onClick = dayPlusOne
        )
    }
}

@Composable
private fun RowScope.DayHeaderText(text: String) {
    Text(
        modifier = Modifier
            .weight(1F)
            .align(CenterVertically),
        text = text,
        style = body2SemiBold,
        color = MaterialTheme.colorScheme.errorContainer,
        textAlign = Center,
    )
}

@Composable
private fun DayHeaderButton(
    icon: Int,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = Modifier.padding(vertical = 6.dp),
        onClick = onClick,
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
        )
    }
}

@Composable
private fun TimeItem(
    first: BookingTime,
    second: BookingTime,
    onClick: (BookingTime) -> Unit,
) {
    Row(
        Modifier
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(end = 16.dp)
    ) {
        TimeItem(Modifier.weight(1F), first, onClick)
        TimeItem(Modifier.weight(1F), second, onClick)
    }
}

@Composable
private fun TimeItem(
    modifier: Modifier = Modifier,
    data: BookingTime,
    onBookingClick: (BookingTime) -> Unit,
) {
    val backgroundColor = when (data.type) {
        Available -> MaterialTheme.colorScheme.primary
        UnAvailable -> MaterialTheme.colorScheme.onPrimary
        Selected -> MaterialTheme.colorScheme.tertiary
    }
    val textColor = when (data.type) {
        Available -> MaterialTheme.colorScheme.surfaceVariant
        UnAvailable -> MaterialTheme.colorScheme.errorContainer
        Selected -> MaterialTheme.colorScheme.surfaceVariant
    }
    val text = "${data.startTime} - ${data.endTime}"
    val onClick = { onBookingClick(data) }
        .takeIf { data.type != UnAvailable }

    TimeItem(
        modifier.testTag(data.type.name),
        backgroundColor,
        text,
        textColor,
        onClick
    )
}

@Composable
private fun TimeItem(
    modifier: Modifier,
    backgroundColor: Color,
    text: String,
    textColor: Color,
    onClick: (() -> Unit)?,
) {
    Text(
        modifier = modifier
            .padding(start = 16.dp)
            .padding(bottom = 8.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .run {
                onClick?.let { clickable(onClick = it) } ?: this
            }
            .padding(vertical = 10.dp),
        color = textColor,
        text = text,
        textAlign = Center,
        style = body3Medium,
    )
}

@Composable
private fun ContinueButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    CUFilledButton(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(1.dp, MaterialTheme.colorScheme.onPrimary)
            .padding(horizontal = 26.dp, vertical = 22.dp)
            .fillMaxWidth(),
        text = stringResource(id = MR.strings.continue1.resourceId),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
        contentPadding = PaddingValues(0.dp),
        onClick = onClick,
    )
}

// region Previews

@Preview
@Composable
private fun AvailableTimeItemPreview() {
    AppTheme {
        TimeItem(
            modifier = Modifier.fillMaxWidth(),
            data = BookingTime(
                0,
                LocalDate.now(),
                "00:00",
                "00:30",
                Available
            ),
            onBookingClick = {},
        )
    }
}

@Preview
@Composable
private fun UnAvailableTimeItemPreview() {
    AppTheme {
        TimeItem(
            modifier = Modifier.fillMaxWidth(),
            data = BookingTime(
                0,
                LocalDate.now(),
                "00:00",
                "00:30",
                UnAvailable,
            ),
            onBookingClick = {},
        )
    }
}

@Preview
@Composable
private fun SelectedTimeItemPreview() {
    AppTheme {
        TimeItem(
            modifier = Modifier.fillMaxWidth(),
            data = BookingTime(
                0,
                LocalDate.now(),
                "00:00",
                "00:30",
                Selected,
            ),
            onBookingClick = {},
        )
    }
}

@Preview(heightDp = 1500)
@Composable
private fun BookScreenPreview() {
    val date = LocalDate.now()
    AppTheme {
        BookingTimeScreen(
            uiState = BookingTimeUiState(
                currentDate = "3.2.2024",
                times = listOf(
                    BookingTime(
                        id = 0,
                        startTime = "00:00",
                        endTime = "00:30",
                        date = date,
                        type = Available,
                    ), BookingTime(
                        id = 30,
                        startTime = "00:30",
                        endTime = "01:00",
                        date = date,
                        type = Selected,
                    ), BookingTime(
                        id = 60,
                        startTime = "01:00",
                        endTime = "01:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 90,
                        startTime = "01:30",
                        endTime = "02:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 120,
                        startTime = "02:00",
                        endTime = "02:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 150,
                        startTime = "02:30",
                        endTime = "03:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 180,
                        startTime = "03:00",
                        endTime = "03:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 210,
                        startTime = "03:30",
                        endTime = "04:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 240,
                        startTime = "04:00",
                        endTime = "04:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 270,
                        startTime = "04:30",
                        endTime = "05:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 300,
                        startTime = "05:00",
                        endTime = "05:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 330,
                        startTime = "05:30",
                        endTime = "06:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 360,
                        startTime = "06:00",
                        endTime = "06:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 390,
                        startTime = "06:30",
                        endTime = "07:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 420,
                        startTime = "07:00",
                        endTime = "07:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 450,
                        startTime = "07:30",
                        endTime = "08:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 480,
                        startTime = "08:00",
                        endTime = "08:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 510,
                        startTime = "08:30",
                        endTime = "09:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 540,
                        startTime = "09:00",
                        endTime = "09:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 570,
                        startTime = "09:30",
                        endTime = "10:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 600,
                        startTime = "10:00",
                        endTime = "10:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 630,
                        startTime = "10:30",
                        endTime = "11:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 660,
                        startTime = "11:00",
                        endTime = "11:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 690,
                        startTime = "11:30",
                        endTime = "12:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 720,
                        startTime = "12:00",
                        endTime = "12:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 750,
                        startTime = "12:30",
                        endTime = "13:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 780,
                        startTime = "13:00",
                        endTime = "13:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 810,
                        startTime = "13:30",
                        endTime = "14:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 840,
                        startTime = "14:00",
                        endTime = "14:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 870,
                        startTime = "14:30",
                        endTime = "15:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 900,
                        startTime = "15:00",
                        endTime = "15:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 930,
                        startTime = "15:30",
                        endTime = "16:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 960,
                        startTime = "16:00",
                        endTime = "16:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 990,
                        startTime = "16:30",
                        endTime = "17:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 1020,
                        startTime = "17:00",
                        endTime = "17:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 1050,
                        startTime = "17:30",
                        endTime = "18:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 1080,
                        startTime = "18:00",
                        endTime = "18:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 1110,
                        startTime = "18:30",
                        endTime = "19:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 1140,
                        startTime = "19:00",
                        endTime = "19:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 1170,
                        startTime = "19:30",
                        endTime = "20:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 1200,
                        startTime = "20:00",
                        endTime = "20:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 1230,
                        startTime = "20:30",
                        endTime = "21:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 1260,
                        startTime = "21:00",
                        endTime = "21:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 1290,
                        startTime = "21:30",
                        endTime = "22:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 1320,
                        startTime = "22:00",
                        endTime = "22:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 1350,
                        startTime = "22:30",
                        endTime = "23:00",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 1380,
                        startTime = "23:00",
                        endTime = "23:30",
                        date = date,
                        type = UnAvailable,
                    ), BookingTime(
                        id = 1410,
                        startTime = "23:30",
                        endTime = "00:00",
                        date = date,
                        type = UnAvailable,
                    )
                )
                    .chunked(2),
            ),
            error = "",
            onErrorDismissed = {},
            dayPlusOne = {},
            dayMinusOne = {},
            onTimeClicked = {},
            onContinueClicked = {},
        )
    }
}

// endregion
