package com.hulkdx.findprofessional.feature.book.summery.stripe

import com.stripe.android.paymentsheet.PaymentSheetResult as StripePaymentSheetResult

fun toStripeCallback(onResult: (PaymentSheetResult) -> Unit): (StripePaymentSheetResult) -> Unit {
    return {
        when (it) {
            is StripePaymentSheetResult.Canceled -> onResult(PaymentSheetResult.Canceled)
            is StripePaymentSheetResult.Failed -> onResult(PaymentSheetResult.Failed(it.error))
            is StripePaymentSheetResult.Completed -> onResult(PaymentSheetResult.Completed)
        }
    }
}
