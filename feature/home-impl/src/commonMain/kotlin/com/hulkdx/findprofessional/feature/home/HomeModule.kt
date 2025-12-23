package com.hulkdx.findprofessional.feature.home

import com.hulkdx.findprofessional.feature.home.detail.HomeDetailViewModel
import com.hulkdx.findprofessional.feature.home.detail.availability.HomeDetailAvailabilityUseCase
import com.hulkdx.findprofessional.feature.home.main.HomeViewModel
import com.hulkdx.findprofessional.feature.home.main.ProfessionalUseCase
import com.hulkdx.findprofessional.feature.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.feature.home.main.api.ProfessionalApiImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeModule: Module
    get() = module {
        viewModelOf(::HomeViewModel)
        factoryOf(::ProfessionalUseCase)
        factoryOf(::ProfessionalApiImpl) bind ProfessionalApi::class

        viewModelOf(::HomeDetailViewModel)
        factoryOf(::HomeDetailAvailabilityUseCase)
    }
