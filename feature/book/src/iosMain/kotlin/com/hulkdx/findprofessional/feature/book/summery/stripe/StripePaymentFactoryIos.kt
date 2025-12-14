package com.hulkdx.findprofessional.feature.book.summery.stripe

import org.koin.core.module.Module
import org.koin.dsl.bind
import platform.UIKit.UIView

interface StripePaymentFactoryIos {
    fun onCreate(): UIView
}

@Suppress("unused") // used in swift
fun Module.addStripePaymentFactoryIos(
    builder: () -> StripePaymentFactoryIos,
) {
    factory { builder() } bind StripePaymentFactoryIos::class
}
