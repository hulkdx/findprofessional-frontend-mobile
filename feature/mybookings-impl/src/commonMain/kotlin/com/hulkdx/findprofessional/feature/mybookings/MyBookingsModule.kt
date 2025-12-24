package com.hulkdx.findprofessional.feature.mybookings

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val myBookingsModule: Module
    get() = module {
        viewModelOf(::MyBookingsViewModel)
    }
