package com.hulkdx.findprofessional.common.feature.book

import com.hulkdx.findprofessional.common.feature.book.summery.BookingSummeryTimeMapper
import com.hulkdx.findprofessional.common.feature.book.summery.BookingSummeryUseCase
import com.hulkdx.findprofessional.common.feature.book.time.BookingTimeUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val bookModule: Module
    get() = module {
        factory {
            BookingTimeUseCase(
                navigator = get()
            )
        }

        factoryOf(::BookingSummeryUseCase)
        factoryOf(::BookingSummeryTimeMapper)
    }
