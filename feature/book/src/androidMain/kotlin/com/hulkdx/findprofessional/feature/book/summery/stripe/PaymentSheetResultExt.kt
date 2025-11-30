package com.hulkdx.findprofessional.feature.book.summery.stripe

import androidx.compose.runtime.State
import com.hulkdx.findprofessional.core.utils.StripeTimedOutException
import com.stripe.android.paymentsheet.PaymentSheetResult as StripePaymentSheetResult

fun createCallback(
    onResult: (PaymentSheetResult) -> Unit,
    isTimedOut: State<Boolean>
): (StripePaymentSheetResult) -> Unit {
    return {
        when (it) {
            is StripePaymentSheetResult.Canceled -> {
                onResult(PaymentSheetResult.Canceled)
            }

            is StripePaymentSheetResult.Failed -> {
                if (isTimedOut.value) {
                    onResult(PaymentSheetResult.Failed(StripeTimedOutException()))
                } else {
                    onResult(PaymentSheetResult.Failed(it.error))
                }
            }

            is StripePaymentSheetResult.Completed -> {
                onResult(PaymentSheetResult.Completed)
            }
        }
    }
}
