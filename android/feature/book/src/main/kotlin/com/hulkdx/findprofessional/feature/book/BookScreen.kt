package com.hulkdx.findprofessional.feature.book

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
import com.hulkdx.findprofessional.common.feature.book.BookUiState
import com.hulkdx.findprofessional.common.feature.book.BookUiState.BookingTime
import com.hulkdx.findprofessional.common.feature.book.BookUiState.BookingTime.Type.Available
import com.hulkdx.findprofessional.common.feature.book.BookUiState.BookingTime.Type.Selected
import com.hulkdx.findprofessional.common.feature.book.BookUiState.BookingTime.Type.UnAvailable
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
fun BookScreen(viewModel: BookViewModel = koinViewModel()) {
    val error by viewModel.error.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BookScreen(
        uiState = uiState ?: return,
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) },
        dayMinusOne = viewModel::dayMinusOne,
        dayPlusOne = viewModel::dayPlusOne,
        onTimeClicked = viewModel::onTimeClicked,
    )
}

@Composable
private fun BookScreen(
    uiState: BookUiState,
    dayMinusOne: () -> Unit,
    dayPlusOne: () -> Unit,
    onTimeClicked: (BookingTime) -> Unit,
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .systemBarsPadding()
            .testTag("BookScreen")
    ) {
        LazyColumn {
            item { Header() }
            item { DayHeader(uiState.currentDate, dayPlusOne, dayMinusOne) }
            items(uiState.times) { (first, second) ->
                TimeItem(first, second, onTimeClicked)
            }
            item { Spacer(modifier = Modifier.height(90.dp)) }
        }
        BottomPart(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {},
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

    TimeItem(modifier, backgroundColor, text, textColor, onClick)
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
private fun BottomPart(
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
    AppTheme {
        BookScreen(
            uiState = BookUiState(
                currentDate = "3.2.2024",
                times = listOf(),
            ),
            error = "",
            onErrorDismissed = {},
            dayPlusOne = {},
            dayMinusOne = {},
            onTimeClicked = {},
        )
    }
}

// endregion
