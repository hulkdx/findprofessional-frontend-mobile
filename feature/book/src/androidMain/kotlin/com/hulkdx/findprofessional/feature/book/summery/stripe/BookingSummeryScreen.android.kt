package com.hulkdx.findprofessional.feature.book.summery.stripe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryUiState
import com.hulkdx.findprofessional.core.features.pro.model.response.CreateBookingResponse
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet

@Composable
actual fun StripePayment(uiState: BookingSummeryUiState, onResult: (PaymentSheetResult) -> Unit) {
    if (uiState.checkoutStatus is BookingSummeryUiState.CheckoutStatus.Success) {
        ShowStripePayment(uiState.checkoutStatus.result, onResult)
    }
}

@Composable
private fun ShowStripePayment(networkResult: CreateBookingResponse, onResult: (PaymentSheetResult) -> Unit) {
    val context = LocalContext.current
    val paymentSheet = remember(onResult) { PaymentSheet.Builder(toStripeCallback(onResult)) }.build()

    LaunchedEffect(context, networkResult) {
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
