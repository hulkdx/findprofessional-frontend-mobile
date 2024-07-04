package com.hulkdx.findprofessional.feature.authentication.splash

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val splashModule: Module
    get() = module {
        factoryOf(::SplashViewModel)
    }
