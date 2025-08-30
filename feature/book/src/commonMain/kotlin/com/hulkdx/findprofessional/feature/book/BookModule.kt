package com.hulkdx.findprofessional.feature.book

import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryTimeMapper
import com.hulkdx.findprofessional.feature.book.summery.usecase.BookingSummeryUseCase
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryViewModel
import com.hulkdx.findprofessional.feature.book.summery.api.PaymentApi
import com.hulkdx.findprofessional.feature.book.summery.api.PaymentApiImpl
import com.hulkdx.findprofessional.feature.book.summery.usecase.CheckoutUseCase
import com.hulkdx.findprofessional.feature.book.time.BookingTimeUseCase
import com.hulkdx.findprofessional.feature.book.time.BookingTimeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val bookModule: Module
    get() = module {
        viewModelOf(::BookingSummeryViewModel)
        factoryOf(::BookingSummeryUseCase)
        factoryOf(::BookingSummeryTimeMapper)
        factoryOf(::CheckoutUseCase)
        factoryOf(::PaymentApiImpl) bind PaymentApi::class

        viewModelOf(::BookingTimeViewModel)
        factory { BookingTimeUseCase(navigator = get()) }
    }
