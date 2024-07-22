package com.hulkdx.findprofessional.feature.book

import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryTimeMapper
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryUseCase
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryViewModel
import com.hulkdx.findprofessional.feature.book.time.BookingTimeUseCase
import com.hulkdx.findprofessional.feature.book.time.BookingTimeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val bookModule: Module
    get() = module {
        factoryOf(::BookingSummeryViewModel)
        factoryOf(::BookingSummeryUseCase)
        factoryOf(::BookingSummeryTimeMapper)

        factoryOf(::BookingTimeViewModel)
        factory { BookingTimeUseCase(navigator = get()) }
    }
