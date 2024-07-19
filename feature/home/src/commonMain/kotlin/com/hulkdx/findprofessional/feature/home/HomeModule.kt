package com.hulkdx.findprofessional.feature.home

import com.hulkdx.findprofessional.feature.home.api.ProfessionalApi
import com.hulkdx.findprofessional.feature.home.api.ProfessionalApiImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module


val homeModule: Module
    get() = module {
        factoryOf(::HomeViewModel)
        factoryOf(::ProfessionalUseCase)
        factoryOf(::ProfessionalApiImpl) bind ProfessionalApi::class
    }
