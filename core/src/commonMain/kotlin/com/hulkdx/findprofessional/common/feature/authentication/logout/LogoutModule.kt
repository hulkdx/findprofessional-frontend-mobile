package com.hulkdx.findprofessional.common.feature.authentication.logout

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val logoutModule: Module
    get() = module {
        factoryOf(::LogoutUseCaseImpl) bind LogoutUseCase::class
    }
