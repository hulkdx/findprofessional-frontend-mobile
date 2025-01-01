package com.hulkdx.findprofessional.feature.pro.auth.signup

import com.hulkdx.findprofessional.feature.pro.auth.signup.usecase.GetProUserUseCase
import com.hulkdx.findprofessional.feature.pro.auth.signup.usecase.GetProUserUseCaseImpl
import com.hulkdx.findprofessional.feature.pro.auth.signup.usecase.SignUpProUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val signUpProModule: Module
    get() = module {
        factoryOf(::SignUpProViewModel)
        factoryOf(::SignUpProUseCase)

        factoryOf(::GetProUserUseCaseImpl) bind GetProUserUseCase::class
    }
