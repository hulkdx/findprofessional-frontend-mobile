package com.hulkdx.findprofessional.feature.home

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val homeModule: Module
    get() = module {
        factoryOf(::HomeViewModel)
    }
