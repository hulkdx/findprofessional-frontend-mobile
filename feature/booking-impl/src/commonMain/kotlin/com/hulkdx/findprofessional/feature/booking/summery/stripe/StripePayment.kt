package com.hulkdx.findprofessional.feature.booking.summery.stripe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.stripePaymentCountdownMessage
import com.hulkdx.findprofessional.core.ui.theme.h2
import com.hulkdx.findprofessional.feature.booking.summery.BookingSummeryUiState
import com.hulkdx.findprofessional.feature.pro.model.response.CreateBookingResponse
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Duration.Companion.minutes

val STRIPE_PAYMENT_SHEET_TIMEOUT = 30.minutes

@Composable
expect fun BoxScope.StripePaymentPlatform(
    networkResult: CreateBookingResponse,
    onResult: (PaymentSheetResult) -> Unit
)

@Composable
fun BoxScope.StripePayment(uiState: BookingSummeryUiState, onResult: (PaymentSheetResult) -> Unit) {

    if (uiState.checkoutStatus is BookingSummeryUiState.CheckoutStatus.Success) {
        ShowInfo()
        StripePaymentPlatform(uiState.checkoutStatus.result, onResult)
    }
}

@Composable
fun ShowInfo() {
    Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.onPrimary)) {
        Text(
            modifier = Modifier.align(Alignment.TopCenter),
            style = h2,
            text = stringResource(
                Res.string.stripePaymentCountdownMessage,
                STRIPE_PAYMENT_SHEET_TIMEOUT.inWholeMinutes,
            ),
        )
    }
}
