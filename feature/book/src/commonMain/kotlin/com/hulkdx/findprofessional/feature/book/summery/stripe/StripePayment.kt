package com.hulkdx.findprofessional.feature.book.summery.stripe

import androidx.compose.runtime.Composable
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryUiState
import kotlin.time.Duration.Companion.minutes

val STRIPE_PAYMENT_SHEET_TIMEOUT = 10.minutes

@Composable
expect fun StripePayment(uiState: BookingSummeryUiState, onResult: (PaymentSheetResult) -> Unit)
