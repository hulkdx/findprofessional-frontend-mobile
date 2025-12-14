package com.hulkdx.findprofessional.feature.book.summery.stripe

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import com.hulkdx.findprofessional.core.features.pro.model.response.CreateBookingResponse
import org.koin.compose.koinInject

@Composable
actual fun BoxScope.StripePaymentPlatform(
    networkResult: CreateBookingResponse,
    onResult: (PaymentSheetResult) -> Unit,
) {
    val factory: StripePaymentFactoryIos = koinInject()
    UIKitView(
        modifier = Modifier.matchParentSize(),
        factory = { factory.onCreate(networkResult, onResult) }
    )
}
