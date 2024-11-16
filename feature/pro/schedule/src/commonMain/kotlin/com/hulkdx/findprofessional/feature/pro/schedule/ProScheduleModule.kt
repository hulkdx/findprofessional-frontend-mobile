package com.hulkdx.findprofessional.feature.pro.schedule

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val proScheduleModule: Module
    get() = module {
        factoryOf(::ProScheduleViewModel)
    }
