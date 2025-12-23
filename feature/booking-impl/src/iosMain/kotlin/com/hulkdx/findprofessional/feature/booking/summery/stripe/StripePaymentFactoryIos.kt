package com.hulkdx.findprofessional.feature.booking.summery.stripe

import com.hulkdx.findprofessional.feature.pro.model.response.CreateBookingResponse
import org.koin.core.module.Module
import org.koin.dsl.bind
import platform.UIKit.UIView

interface StripePaymentFactoryIos {
    fun onCreate(
        networkResult: CreateBookingResponse,
        onResult: (PaymentSheetResult) -> Unit,
    ): UIView
}

@Suppress("unused") // used in swift
fun Module.addStripePaymentFactoryIos(
    builder: () -> StripePaymentFactoryIos,
) {
    factory { builder() } bind StripePaymentFactoryIos::class
}
