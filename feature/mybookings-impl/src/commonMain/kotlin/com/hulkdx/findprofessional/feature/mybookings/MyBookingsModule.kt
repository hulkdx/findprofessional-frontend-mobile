package com.hulkdx.findprofessional.feature.mybookings

import com.hulkdx.findprofessional.feature.mybookings.usecase.GetMyBookingsUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val myBookingsModule: Module
    get() = module {
        viewModelOf(::MyBookingsViewModel)
        factoryOf(::GetMyBookingsUseCase)
    }
