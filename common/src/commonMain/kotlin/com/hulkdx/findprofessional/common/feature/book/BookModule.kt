package com.hulkdx.findprofessional.common.feature.book

import com.hulkdx.findprofessional.common.feature.book.time.BookingTimeUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val bookModule: Module
    get() = module {
        factory {
            BookingTimeUseCase()
        }
    }
