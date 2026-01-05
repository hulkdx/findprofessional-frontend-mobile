package com.hulkdx.findprofessional.feature.mybookings

import com.hulkdx.findprofessional.feature.mybookings.usecase.GetMyBookingsUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val myBookingsModule: Module
    get() = module {
        viewModel {
            MyBookingsViewModel(get())
        }
        factoryOf(::GetMyBookingsUseCase)
    }
