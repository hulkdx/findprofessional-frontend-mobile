package com.hulkdx.findprofessional.feature.mybookings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.bookingPast
import com.hulkdx.findprofessional.core.resources.bookingUpcoming
import com.hulkdx.findprofessional.core.resources.help
import com.hulkdx.findprofessional.core.resources.myBookingsTitle
import com.hulkdx.findprofessional.core.resources.reportProblem
import com.hulkdx.findprofessional.core.ui.commonui.navbar.AppNavBarContainer
import com.hulkdx.findprofessional.core.ui.commonui.navbar.AppNavigationBarDimens
import com.hulkdx.findprofessional.core.ui.theme.AppTheme
import com.hulkdx.findprofessional.core.ui.theme.body1Medium
import com.hulkdx.findprofessional.core.ui.theme.body2
import com.hulkdx.findprofessional.core.ui.theme.body2Medium
import com.hulkdx.findprofessional.core.ui.theme.body3
import com.hulkdx.findprofessional.core.ui.theme.body3Medium
import com.hulkdx.findprofessional.core.ui.theme.h1Medium
import com.hulkdx.findprofessional.core.ui.theme.h3Medium
import com.hulkdx.findprofessional.feature.mybookings.model.BookingStatus
import com.hulkdx.findprofessional.feature.mybookings.model.BookingUiState
import com.hulkdx.findprofessional.feature.mybookings.model.MyBookingSegment
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MyBookingsScreen(
    viewModel: MyBookingsViewModel = koinViewModel(),
) {
    MyBookingsScreen(
        uiStatus = BookingUiState(listOf()),
        onSegmentSelected = viewModel::onSegmentSelected,
        onClickCancel = viewModel::onClickCancel,
        onClickReportProblem = viewModel::onClickReportProblem,
        onClickJoinSession = viewModel::onClickJoinSession,
        error = null,
        onErrorDismissed = viewModel::onErrorDismissed,
    )
}

@Composable
fun MyBookingsScreen(
    uiStatus: BookingUiState,
    onClickReportProblem: () -> Unit,
    onClickCancel: () -> Unit,
    onSegmentSelected: (MyBookingSegment) -> Unit,
    onClickJoinSession: () -> Unit,
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    AppNavBarContainer(
        modifier = Modifier.testTag("MyBookingsScreen"),
        error = error,
        onErrorDismissed = onErrorDismissed,
    ) {
        MyBookingsScreenContent(
            uiStatus = uiStatus,
            onSegmentSelected = onSegmentSelected,
            onClickReportProblem = onClickReportProblem,
            onClickCancel = onClickCancel,
            onClickJoinSession = onClickJoinSession,
        )
    }
}

@Composable
fun MyBookingsScreenContent(
    uiStatus: BookingUiState,
    onClickReportProblem: () -> Unit,
    onClickCancel: () -> Unit,
    onClickJoinSession: () -> Unit,
    onSegmentSelected: (MyBookingSegment) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(bottom = AppNavigationBarDimens.Height.dp)
    ) {
        stickyHeader(key = "stickyHeader") {
            Column(Modifier.background(MaterialTheme.colorScheme.onPrimary)) {
                Header()
                SegmentHeader(onSegmentSelected)
            }
        }
        items(
            items = uiStatus.items,
            key = { it.id },
        ) {
            BookingCard(
                booking = it,
                onClickReportProblem = onClickReportProblem,
                onClickCancel = onClickCancel,
                onClickJoinSession = onClickJoinSession,
            )
        }
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            style = h1Medium,
            textAlign = TextAlign.Start,
            text = stringResource(Res.string.myBookingsTitle),
        )
        Spacer(modifier = Modifier.weight(1f))
        HelpHeader()
    }
}

@Composable
private fun HelpHeader() {
    var showHelpDialog by remember { mutableStateOf(false) }
    IconButton(
        modifier = Modifier,
        onClick = { showHelpDialog = true },
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.HelpOutline,
            contentDescription = stringResource(Res.string.help),
        )
    }
    if (showHelpDialog) {
        BookingHelpDialog(onDismiss = {
            @Suppress("AssignedValueIsNeverRead")
            showHelpDialog = false
        })
    }
}

@Composable
private fun SegmentHeader(
    onSelected: (MyBookingSegment) -> Unit,
) {
    var selected by remember { mutableStateOf(MyBookingSegment.Upcoming) }
    LaunchedEffect(selected, onSelected) {
        onSelected(selected)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(18.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onTertiary,
                shape = RoundedCornerShape(18.dp),
            )
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        SegmentedItem(
            text = Res.string.bookingUpcoming,
            isSelected = selected == MyBookingSegment.Upcoming,
            onClick = {
                selected = MyBookingSegment.Upcoming
            },
            modifier = Modifier.weight(1f),
        )
        SegmentedItem(
            text = Res.string.bookingPast,
            isSelected = selected == MyBookingSegment.Past,
            onClick = {
                selected = MyBookingSegment.Past
            },
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun SegmentedItem(
    text: StringResource,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(32.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(
                if (isSelected) {
                    MaterialTheme.colorScheme.surfaceVariant
                } else {
                    MaterialTheme.colorScheme.onPrimary
                }
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(text),
            style = body2Medium,
            color = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
        )
    }
}

@Composable
private fun BookingCard(
    booking: BookingUiState.Item,
    onClickReportProblem: () -> Unit,
    onClickCancel: () -> Unit,
    onClickJoinSession: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onTertiary,
                shape = RoundedCornerShape(16.dp),
            )
            .padding(12.dp),
    ) {
        BookingCardTopRow(booking)
        Spacer(modifier = Modifier.height(10.dp))
        BookingCardButtons(booking, onClickReportProblem, onClickCancel, onClickJoinSession)
    }
}

@Composable
private fun BookingCardTopRow(booking: BookingUiState.Item) {
    Row(
        verticalAlignment = Alignment.Top,
    ) {
        BookingDate(
            dayLabel = booking.dayLabel,
            dayNumber = booking.dayNumber,
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                BookingName(booking)
                Spacer(modifier = Modifier.weight(1f))
                BookingStatusChip(booking.status)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = booking.type,
                style = body3Medium,
                color = MaterialTheme.colorScheme.errorContainer,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = booking.startTime,
                style = body3,
                color = MaterialTheme.colorScheme.errorContainer,
            )
        }
    }
}

@Composable
private fun BookingDate(
    dayLabel: String,
    dayNumber: String,
) {
    Column(
        modifier = Modifier
            .width(45.dp)
            .background(MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(12.dp))
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = dayLabel,
            style = body3Medium,
            color = MaterialTheme.colorScheme.errorContainer,
        )
        Text(
            text = dayNumber,
            style = h3Medium,
            color = MaterialTheme.colorScheme.outline,
        )
    }
}

@Composable
private fun BookingName(booking: BookingUiState.Item) {
    Text(
        text = booking.fullName,
        style = body1Medium,
        color = MaterialTheme.colorScheme.outline,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun BookingStatusChip(status: BookingStatus) {
    val (background, foreground) = when (status) {
        BookingStatus.Confirmed -> Pair(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
            MaterialTheme.colorScheme.primary,
        )

        else -> Pair(
            MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f),
            MaterialTheme.colorScheme.secondary,
        )
    }

    Box(
        modifier = Modifier
            .background(background, RoundedCornerShape(10.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
    ) {
        Text(
            text = status.name.uppercase(),
            style = body3Medium,
            color = foreground,
        )
    }
}

@Composable
private fun ColumnScope.BookingCardButtons(
    booking: BookingUiState.Item,
    onClickReportProblem: () -> Unit,
    onClickCancel: () -> Unit,
    onClickJoinSession: () -> Unit,
) {
    if (booking.status == BookingStatus.Confirmed) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            OutlinedActionButton(
                text = stringResource(Res.string.reportProblem),
                onClick = onClickJoinSession,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.outline,
            )
            OutlinedActionButton(
                text = stringResource(Res.string.help),
                onClick = onClickCancel,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.secondary,
            )

        }
    }
    Spacer(modifier = Modifier.height(2.dp))
    OutlinedActionButton(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(start = 5.dp)
            .align(Alignment.End),
        text = stringResource(Res.string.reportProblem),
        onClick = onClickReportProblem,
        color = MaterialTheme.colorScheme.secondary,
    )
}

@Composable
private fun OutlinedActionButton(
    text: String,
    onClick: () -> Unit,
    color: Color,
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(36.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = color),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onTertiary),
        contentPadding = PaddingValues(horizontal = 8.dp),
    ) {
        Text(
            text = text,
            style = body2,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview
@Composable
private fun MyBookingsScreenPreview() {
    AppTheme {
        MyBookingsScreen(
            uiStatus = BookingUiState(
                items = listOf(
                    BookingUiState.Item(
                        id = "1",
                        "Mon",
                        "16",
                        "Sarah Adams",
                        BookingStatus.Confirmed,
                        "Fitness coaching",
                        "09:00 • 45 min",
                    ),
                    BookingUiState.Item(
                        id = "2",
                        "Mon",
                        "17",
                        "Sarah Adams",
                        BookingStatus.Canceled,
                        "Life coaching",
                        "13:00 • 45 min",
                    ),
                )
            ),
            error = null,
            onErrorDismissed = {},
            onSegmentSelected = {},
            onClickCancel = {},
            onClickReportProblem = {},
            onClickJoinSession = {},
        )
    }
}
