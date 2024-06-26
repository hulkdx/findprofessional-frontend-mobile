package com.hulkdx.findprofessional.common.feature.authentication.signup

import com.hulkdx.findprofessional.common.feature.authentication.pro.signup.SignUpProUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val signUpModule: Module
    get() = module {
        factoryOf(::SignUpApiImpl) bind SignUpApi::class
        factoryOf(::SignUpUseCase)

        factoryOf(::SignUpProUseCase)
    }
