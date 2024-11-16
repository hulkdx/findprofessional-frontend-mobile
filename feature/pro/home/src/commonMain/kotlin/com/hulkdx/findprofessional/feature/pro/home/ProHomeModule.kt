package com.hulkdx.findprofessional.feature.pro.home

import com.hulkdx.findprofessional.feature.pro.home.tmp.EditAvailableTimeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val proHomeModule: Module
    get() = module {
        factoryOf(::ProHomeViewModel)

        factoryOf(::EditAvailableTimeViewModel)
    }
