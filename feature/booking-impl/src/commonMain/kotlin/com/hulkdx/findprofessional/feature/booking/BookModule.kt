package com.hulkdx.findprofessional.feature.booking

import com.hulkdx.findprofessional.feature.booking.summery.BookingSummeryTimeMapper
import com.hulkdx.findprofessional.feature.booking.summery.BookingSummeryViewModel
import com.hulkdx.findprofessional.feature.booking.summery.usecase.BookingSummeryUseCase
import com.hulkdx.findprofessional.feature.booking.summery.usecase.CheckBookingStatusUseCase
import com.hulkdx.findprofessional.feature.booking.summery.usecase.CreateBookingUseCase
import com.hulkdx.findprofessional.feature.booking.time.BookingTimeUseCase
import com.hulkdx.findprofessional.feature.booking.time.BookingTimeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val bookModule: Module
    get() = module {
        viewModelOf(::BookingSummeryViewModel)
        factoryOf(::BookingSummeryUseCase)
        factoryOf(::BookingSummeryTimeMapper)
        factoryOf(::CreateBookingUseCase)
        factoryOf(::CheckBookingStatusUseCase)

        viewModelOf(::BookingTimeViewModel)
        factoryOf(::BookingTimeUseCase)
    }
