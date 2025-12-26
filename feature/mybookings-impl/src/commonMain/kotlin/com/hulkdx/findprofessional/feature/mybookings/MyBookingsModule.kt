package com.hulkdx.findprofessional.feature.mybookings

import com.hulkdx.findprofessional.feature.mybookings.api.MyBookingApi
import com.hulkdx.findprofessional.feature.mybookings.api.MyBookingApiImpl
import com.hulkdx.findprofessional.feature.mybookings.usecase.GetMyBookingsUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val myBookingsModule: Module
    get() = module {
        viewModelOf(::MyBookingsViewModel)
        factoryOf(::GetMyBookingsUseCase)
        factoryOf(::MyBookingApiImpl) bind MyBookingApi::class
    }
