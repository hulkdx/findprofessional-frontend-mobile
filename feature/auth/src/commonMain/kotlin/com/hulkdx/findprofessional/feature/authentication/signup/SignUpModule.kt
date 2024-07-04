package com.hulkdx.findprofessional.feature.authentication.signup

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val signUpModule: Module
    get() = module {
        factoryOf(::SignUpViewModel)
    }
