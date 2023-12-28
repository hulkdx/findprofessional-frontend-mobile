package com.hulkdx.findprofessional.common.feature.home

import com.hulkdx.findprofessional.common.feature.home.detail.HomeDetailAvailabilityUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeModule: Module
    get() = module {
        factoryOf(::ProfessionalApiImpl) bind ProfessionalApi::class
        factoryOf(::HomeUseCase)
        factoryOf(::HomeDetailAvailabilityUseCase)
    }
