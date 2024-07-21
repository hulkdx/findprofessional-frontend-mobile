package com.hulkdx.findprofessional.feature.book

import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val bookModule: Module
    get() = module {
        factoryOf(::BookingSummeryViewModel)
    }
