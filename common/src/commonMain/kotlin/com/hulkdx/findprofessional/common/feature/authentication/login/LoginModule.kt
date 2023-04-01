package com.hulkdx.findprofessional.common.feature.authentication.login

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val loginModule: Module
    get() = module {
        factoryOf(::LoginUseCase)
        factoryOf(::LoginApiImpl) bind LoginApi::class
        factoryOf(::RefreshTokenApiImpl) bind RefreshTokenApi::class
    }
