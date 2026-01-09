package com.hulkdx.findprofessional.feature.pro.schedule.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.bookingIdEquals
import com.hulkdx.findprofessional.core.resources.cannotOpenUrl
import com.hulkdx.findprofessional.core.resources.joinSession
import com.hulkdx.findprofessional.core.ui.commonui.CUFilledButton
import com.hulkdx.findprofessional.core.ui.commonui.navbar.AppNavigationBarDimens
import com.hulkdx.findprofessional.core.ui.commonui.navbar.ProAppNavBarContainer
import com.hulkdx.findprofessional.core.ui.theme.body1Medium
import com.hulkdx.findprofessional.core.ui.theme.body3
import com.hulkdx.findprofessional.core.ui.theme.body3Medium
import com.hulkdx.findprofessional.core.ui.theme.h3Medium
import com.hulkdx.findprofessional.core.utils.toStringOrRes
import com.hulkdx.findprofessional.feature.pro.model.Booking
import com.hulkdx.findprofessional.feature.pro.model.Booking.Status.COMPLETED
import com.hulkdx.findprofessional.feature.pro.model.Booking.Status.CONFIRMED
import com.hulkdx.findprofessional.feature.pro.schedule.ProScheduleViewModel
import com.hulkdx.findprofessional.feature.pro.schedule.model.ScheduleUiState
import com.hulkdx.findprofessional.feature.pro.schedule.model.ScheduleUiState.Item
import com.hulkdx.findprofessional.feature.pro.schedule.model.Segment
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProScheduleScreen(viewModel: ProScheduleViewModel = koinViewModel()) {
    val uiState by viewModel.state.collectAsState()
    val uriHandler = LocalUriHandler.current

    val navigation = uiState.navigation
    LaunchedEffect(navigation) {
        when (navigation) {
            is ScheduleUiState.Navigation.OpenUrl -> {
                runCatching {
                    uriHandler.openUri(navigation.url)
                }.onFailure {
                    viewModel.setError(
                        getString(Res.string.cannotOpenUrl, navigation.url)
                            .toStringOrRes()
                    )
                }
            }

            null -> {
                // no-op
            }
        }
        viewModel.onNavigated()
    }

    ProScheduleScreen(
        uiState = uiState,
        onRefresh = viewModel::onRefresh,
        onSegmentSelected = viewModel::onSegmentSelected,
        onClickJoinSession = viewModel::onClickJoinSession,
        error = uiState.error?.localized(),
        onErrorDismissed = { viewModel.setError(null) },
    )
}

@Composable
fun ProScheduleScreen(
    uiState: ScheduleUiState,
    onRefresh: () -> Unit,
    onSegmentSelected: (Segment) -> Unit,
    onClickJoinSession: (Item) -> Unit,
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    ProAppNavBarContainer(
        modifier = Modifier.testTag("ProScheduleScreen"),
        error = error,
        onErrorDismissed = onErrorDismissed,
    ) {
        PullToRefreshBox(
            isRefreshing = uiState.isRefreshing,
            onRefresh = onRefresh,
        ) {
            Content(
                uiState = uiState,
                onSegmentSelected = onSegmentSelected,
                onClickJoinSession = onClickJoinSession,
            )
        }
    }
}

@Composable
private fun Content(
    uiState: ScheduleUiState,
    onSegmentSelected: (Segment) -> Unit,
    onClickJoinSession: (Item) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(bottom = AppNavigationBarDimens.Height.dp)
    ) {
        stickyHeader(key = "stickyHeader") {
            Column(
                modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
                    .padding(bottom = 16.dp)
            ) {
                Header(uiState.segment, onSegmentSelected)
            }
        }
        items(
            items = uiState.items,
            key = { it.id },
        ) {
            BookingCard(
                item = it,
                onClickJoinSession = onClickJoinSession,
            )
        }
    }
}


@Composable
private fun BookingCard(
    item: Item,
    onClickJoinSession: (Item) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onTertiary,
                shape = RoundedCornerShape(16.dp),
            )
            .padding(12.dp),
    ) {
        BookingCardTopRow(item)
        Spacer(modifier = Modifier.height(10.dp))
        BookingCardButtons(
            item,
            onClickJoinSession,
        )
    }
}

@Composable
private fun BookingCardTopRow(item: Item) {
    Row(
        verticalAlignment = Alignment.Top,
    ) {
        BookingDate(
            dayLabel = item.dayLabel,
            dayNumber = item.dayNumber,
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                BookingName(item)
                Spacer(modifier = Modifier.weight(1f))
                BookingStatusChip(item.status)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.startTime,
                style = body3,
                color = MaterialTheme.colorScheme.errorContainer,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = stringResource(Res.string.bookingIdEquals, item.id),
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
private fun BookingName(booking: Item) {
    Text(
        text = booking.fullName,
        style = body1Medium,
        color = MaterialTheme.colorScheme.outline,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun BookingStatusChip(status: Booking.Status) {
    val (background, foreground) = when (status) {
        COMPLETED, CONFIRMED -> Pair(
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
private fun BookingCardButtons(
    item: Item,
    onClickJoinSession: (Item) -> Unit,
) {
    if (!item.canJoin) {
        return
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        JoinSession(
            modifier = Modifier.weight(1f),
            onClickJoinSession = { onClickJoinSession(item) },
        )
        Spacer(Modifier.weight(1f))
    }
}

@Composable
private fun JoinSession(
    modifier: Modifier = Modifier,
    onClickJoinSession: () -> Unit,
) {
    CUFilledButton(
        text = stringResource(Res.string.joinSession),
        onClick = onClickJoinSession,
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 0.dp, horizontal = 8.dp),
    )
}
