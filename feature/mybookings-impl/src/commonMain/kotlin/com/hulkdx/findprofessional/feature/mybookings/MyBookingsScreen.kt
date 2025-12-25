package com.hulkdx.findprofessional.feature.mybookings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.bookingHelp
import com.hulkdx.findprofessional.core.resources.bookingPast
import com.hulkdx.findprofessional.core.resources.bookingUpcoming
import com.hulkdx.findprofessional.core.resources.myBookingsTitle
import com.hulkdx.findprofessional.core.ui.commonui.navbar.AppNavBarContainer
import com.hulkdx.findprofessional.core.ui.commonui.navbar.AppNavigationBarDimens
import com.hulkdx.findprofessional.core.ui.theme.AppTheme
import com.hulkdx.findprofessional.core.ui.theme.body2Medium
import com.hulkdx.findprofessional.core.ui.theme.h1Medium
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
        onSegmentSelected = {},
        error = null,
        onErrorDismissed = {}
    )
}

@Composable
fun MyBookingsScreen(
    onSegmentSelected: (MyBookingSegment) -> Unit,
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    AppNavBarContainer(
        modifier = Modifier.testTag("MyBookingsScreen"),
        error = error,
        onErrorDismissed = onErrorDismissed,
    ) {
        MyBookingsScreenContent(
            onSegmentSelected = onSegmentSelected,
        )
    }
}

@Composable
fun MyBookingsScreenContent(
    onSegmentSelected: (MyBookingSegment) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .verticalScroll(rememberScrollState())
            .padding(bottom = AppNavigationBarDimens.Height.dp)
    ) {
        Header()
        SegmentHeader(onSelected = onSegmentSelected)
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
            contentDescription = stringResource(Res.string.bookingHelp),
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

@Preview
@Composable
private fun MyBookingsScreenPreview() {
    AppTheme {
        MyBookingsScreen(
            error = null,
            onErrorDismissed = {},
            onSegmentSelected = {},
        )
    }
}
