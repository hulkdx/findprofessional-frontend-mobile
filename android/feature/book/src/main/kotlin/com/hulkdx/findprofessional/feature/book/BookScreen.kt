package com.hulkdx.findprofessional.feature.book

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.core.commonui.CUFilledButton
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body2SemiBold
import com.hulkdx.findprofessional.core.theme.body3Medium
import com.hulkdx.findprofessional.core.theme.h2Medium
import com.hulkdx.findprofessional.feature.book.utils.BOOKING_TIMES
import com.hulkdx.findprofessional.resources.MR
import dev.icerock.moko.resources.compose.localized
import org.koin.androidx.compose.koinViewModel


@Composable
fun BookScreen(viewModel: BookViewModel = koinViewModel()) {
    val error by viewModel.error.collectAsStateWithLifecycle()

    BookScreen(
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) },
    )
}

@Composable
private fun BookScreen(
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
            item { AvailabilityCalendarTopHeader() }
            items(BOOKING_TIMES) {
                TimeItem(it)
            }
            item { Bottom({}) }
        }
        CUSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            message = error,
            onDismiss = onErrorDismissed
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
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun AvailabilityCalendarTopHeader(
//    availability: AvailabilityData,
//    availabilityMonthMinusOne: () -> Unit,
//    availabilityMonthPlusOne: () -> Unit,
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
        AvailabilityCalendarTopHeaderButton(
            icon = R.drawable.ic_calendar_left,
//            onClick = availabilityMonthMinusOne,
            onClick = {},
        )
        AvailabilityCalendarTopHeaderMainText("2020 January 10 , Monday")
        AvailabilityCalendarTopHeaderButton(
            icon = R.drawable.ic_calendar_right,
            onClick = { }
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
        style = body2SemiBold,
        color = Color(0xFF9D9CAC),
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
private fun TimeItem(times: List<Pair<String, String>>) {
    val (first, second) = times
    Row(
        Modifier
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(end = 16.dp)
    ) {
        TimeItem(first)
        TimeItem(second)
    }
}

@Composable
private fun RowScope.TimeItem(time: Pair<String, String>) {
    val firstTime = time.first
    val secondTime = time.second

    Text(
        modifier = Modifier
            .weight(1F)
            .padding(start = 16.dp)
            .padding(bottom = 8.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(Color(0xFF00B262))
            .padding(vertical = 10.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        text = "$firstTime - $secondTime",
        textAlign = TextAlign.Center,
        style = body3Medium,
    )
}

@Composable
private fun Bottom(onClick: () -> Unit) {
    CUFilledButton(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(1.dp, MaterialTheme.colorScheme.onPrimary)
            .padding(horizontal = 26.dp, vertical = 22.dp)
            .fillMaxWidth(),
        text = stringResource(id = MR.strings.bookNow.resourceId),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
        contentPadding = PaddingValues(0.dp),
        onClick = onClick,
    )
}

@Preview(heightDp = 1500)
@Composable
private fun BookScreenPreview() {
    AppTheme {
        BookScreen(
            error = "",
            onErrorDismissed = {},
        )
    }
}
