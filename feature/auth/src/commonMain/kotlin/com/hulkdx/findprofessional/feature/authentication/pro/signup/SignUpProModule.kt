package com.hulkdx.findprofessional.feature.authentication.pro.signup

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val signUpProModule: Module
    get() = module {
        factoryOf(::SignUpProViewModel)
    }
