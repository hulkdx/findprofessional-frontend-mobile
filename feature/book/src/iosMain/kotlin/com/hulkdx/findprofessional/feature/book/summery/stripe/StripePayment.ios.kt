package com.hulkdx.findprofessional.feature.book.summery.stripe

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import com.hulkdx.findprofessional.core.features.pro.model.response.CreateBookingResponse
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryUiState

@Composable
actual fun BoxScope.StripePaymentPlatform(
    networkResult: CreateBookingResponse,
    onResult: (PaymentSheetResult) -> Unit,
) {
    // TODO: 
}
