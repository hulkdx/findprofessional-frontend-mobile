package com.hulkdx.findprofessional.feature.profile

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val profileModule: Module
    get() = module {
        viewModelOf(::ProfileViewModel)
    }
