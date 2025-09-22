package com.hulkdx.findprofessional.feature.book.summery.stripe

import androidx.compose.runtime.Composable
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryUiState

@Composable
expect fun StripePayment(uiState: BookingSummeryUiState, onResult: (PaymentSheetResult) -> Unit)
