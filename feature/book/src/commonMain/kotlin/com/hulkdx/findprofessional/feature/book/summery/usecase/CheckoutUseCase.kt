package com.hulkdx.findprofessional.feature.book.summery.usecase

import com.hulkdx.findprofessional.feature.book.summery.api.PayRequest
import com.hulkdx.findprofessional.feature.book.summery.api.PaymentApi

class CheckoutUseCase(
    private val api: PaymentApi,
) {
    suspend fun execute(request: PayRequest) = runCatching {
        api.checkout(request)
    }
}
