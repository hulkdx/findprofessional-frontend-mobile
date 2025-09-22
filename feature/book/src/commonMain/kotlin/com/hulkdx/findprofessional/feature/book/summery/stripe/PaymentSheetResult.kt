package com.hulkdx.findprofessional.feature.book.summery.stripe

/**
 * Clone of com.stripe.android.paymentsheet.PaymentSheetResult
 */
sealed class PaymentSheetResult {
    data object Completed : PaymentSheetResult()
    data object Canceled : PaymentSheetResult()
    data class Failed(val error: Throwable) : PaymentSheetResult()
}
