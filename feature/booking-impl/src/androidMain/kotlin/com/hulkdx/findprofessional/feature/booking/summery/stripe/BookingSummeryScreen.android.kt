package com.hulkdx.findprofessional.feature.booking.summery.stripe

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.hulkdx.findprofessional.feature.pro.model.response.CreateBookingResponse
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet

@Composable
actual fun BoxScope.StripePaymentPlatform(
    networkResult: CreateBookingResponse,
    onResult: (PaymentSheetResult) -> Unit
) {
    val context = LocalContext.current

    val isTimedOut = remember { mutableStateOf(false) }

    val paymentSheet =
        remember(onResult) { PaymentSheet.Builder(createCallback(onResult, isTimedOut)) }.build()

    AutoDismissPaymentSheet(timeout = STRIPE_PAYMENT_SHEET_TIMEOUT, context = context, isTimedOut)

    LaunchedEffect(context, networkResult) {
        isTimedOut.value = false

        val currentConfig = PaymentSheet.CustomerConfiguration.createWithCustomerSession(
            id = networkResult.customer,
            clientSecret = networkResult.customerSessionClientSecret
        )
        PaymentConfiguration.init(context, networkResult.publishableKey)
        presentPaymentSheet(paymentSheet, currentConfig, networkResult.paymentIntent)
    }

}

private fun presentPaymentSheet(
    paymentSheet: PaymentSheet,
    customerConfig: PaymentSheet.CustomerConfiguration,
    paymentIntentClientSecret: String
) {
    paymentSheet.presentWithPaymentIntent(
        paymentIntentClientSecret,
        PaymentSheet.Configuration.Builder(merchantDisplayName = "My merchant name")
            .customer(customerConfig)
            // Set `allowsDelayedPaymentMethods` to true if your business handles
            // delayed notification payment methods like US bank accounts.
            .allowsDelayedPaymentMethods(true)
            .build()
    )
}
