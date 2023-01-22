package com.hulkdx.findprofessional.common.di.module

import com.hulkdx.findprofessional.common.feature.authentication.login.LoginUseCase
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val repositoryModule: Module
    get() = module {
        factoryOf(::LoginUseCase)
        factoryOf(::SignUpUseCase)
    }
