package com.hulkdx.findprofessional.feature.book.summery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.common.feature.book.summery.BookingSummeryUiState
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.core.commonui.CUFilledButton
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body1Medium
import com.hulkdx.findprofessional.core.theme.body2
import com.hulkdx.findprofessional.core.theme.h1Medium
import com.hulkdx.findprofessional.resources.MR
import dev.icerock.moko.resources.compose.localized
import org.koin.androidx.compose.koinViewModel


@Composable
fun BookingSummeryScreen(viewModel: BookingSummeryViewModel = koinViewModel()) {
    val error by viewModel.error.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BookingSummeryScreen(
        uiState = uiState ?: return,
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) }
    )
}

@Composable
fun BookingSummeryScreen(
    uiState: BookingSummeryUiState,
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .systemBarsPadding()
            .testTag("BookingSummeryScreen")
    ) {
        LazyColumn {
            item { YourRequest() }
            item { DateAndTime() }
            items(uiState.times) {
                TimeItem(it)
            }
            item { Connection(uiState.userSkypeId) }
        }
        Bottom(
            modifier = Modifier.align(Alignment.BottomStart),
            totalPrices = uiState.totalPrices,
            onCheckoutClicked = {},
        )
        CUSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            message = error,
            onDismiss = onErrorDismissed
        )
    }
}

@Composable
private fun YourRequest() {
    Text(
        modifier = Modifier
            .padding(top = 100.dp)
            .fillMaxWidth(),
        text = stringResource(id = MR.strings.yourRequest.resourceId),
        textAlign = TextAlign.Center,
        style = h1Medium,
    )
}

@Composable
private fun DateAndTime() {
    Text(
        modifier = Modifier
            .padding(
                top = 32.dp,
                start = 26.dp
            ),
        text = stringResource(id = MR.strings.dateAndTime.resourceId),
        style = body1Medium,
    )
}

@Composable
private fun TimeItem(data: BookingSummeryUiState.Time) {
    Row(
        modifier = Modifier
            .padding(horizontal = 26.dp)
            .padding(top = 8.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = data.day,
            style = body2,
        )
        Spacer(Modifier.weight(1F))
        Text(
            text = data.duration,
            style = body2,
        )
        Spacer(Modifier.weight(1F))
        Text(
            text = data.date,
            style = body2,
        )
        Spacer(Modifier.weight(0.5F))
        Image(painter = painterResource(R.drawable.ic_edit_request), contentDescription = null)
    }
}

@Composable
private fun Connection(userEmail: String) {
    Column {
        Text(
            modifier = Modifier
                .padding(
                    top = 32.dp,
                    start = 26.dp
                ),
            text = stringResource(id = MR.strings.connection.resourceId),
            style = body1Medium,
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 26.dp)
                .padding(top = 8.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = stringResource(id = MR.strings.skypeId.resourceId),
                style = body2,
            )
            Spacer(Modifier.weight(2F))
            Text(
                text = userEmail,
                style = body2,
            )
            Spacer(Modifier.weight(1F))
            Image(painter = painterResource(R.drawable.ic_edit_request), contentDescription = null)
        }
    }
}

@Composable
private fun Bottom(
    modifier: Modifier = Modifier,
    totalPrices: String,
    onCheckoutClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(1.dp, MaterialTheme.colorScheme.onPrimary)
            .padding(horizontal = 26.dp, vertical = 22.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = stringResource(id = MR.strings.total.resourceId),
                style = body1Medium,
            )
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = totalPrices,
                style = body1Medium,
            )
        }
        CheckoutButton(onCheckoutClicked)
    }
}

@Composable
private fun CheckoutButton(onClick: () -> Unit) {
    CUFilledButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = MR.strings.checkout.resourceId),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
        contentPadding = PaddingValues(0.dp),
        onClick = onClick,
    )
}

@Preview
@Composable
private fun BookingSummeryScreenPreview() {
    AppTheme {
        BookingSummeryScreen(
            error = "",
            onErrorDismissed = {},
            uiState = BookingSummeryUiState(
                userSkypeId = "test@gmail.com",
                times = listOf(
                    BookingSummeryUiState.Time(
                        duration = "16:30 - 17:00",
                        date = "1.1.2024",
                        day = "Mon",
                    ),
                    BookingSummeryUiState.Time(
                        duration = "17:30 - 18:00",
                        date = "1.1.2024",
                        day = "Mon",
                    ),
                ),
                totalPrices = "100 €"
            ),
        )
    }
}
