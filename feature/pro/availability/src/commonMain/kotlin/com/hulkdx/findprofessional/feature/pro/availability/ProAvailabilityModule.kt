package com.hulkdx.findprofessional.feature.pro.availability

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val proAvailabilityModule: Module
    get() = module {
        factoryOf(::ProAvailabilityViewModel)
    }
