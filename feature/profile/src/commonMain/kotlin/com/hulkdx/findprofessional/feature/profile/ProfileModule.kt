package com.hulkdx.findprofessional.feature.profile

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val profileModule: Module
    get() = module {
        factoryOf(::ProfileViewModel)
    }
