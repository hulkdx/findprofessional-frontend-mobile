package com.hulkdx.findprofessional.feature.pro.availability

import com.hulkdx.findprofessional.feature.pro.availability.detail.ProAvailabilityDetailViewModel
import com.hulkdx.findprofessional.feature.pro.availability.detail.usecase.UpdateAvailabilityUseCase
import com.hulkdx.findprofessional.feature.pro.availability.main.ProAvailabilityViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val proAvailabilityModule: Module
    get() = module {
        viewModelOf(::ProAvailabilityViewModel)
        viewModelOf(::ProAvailabilityDetailViewModel)
        factoryOf(::UpdateAvailabilityUseCase)
    }
