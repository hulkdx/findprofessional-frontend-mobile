package com.hulkdx.findprofessional.common.di.module

import com.hulkdx.findprofessional.common.feature.authentication.login.LoginRepository
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val repositoryModule: Module
    get() = module {
        factoryOf(::LoginRepository)
        factoryOf(::SignUpRepository)
    }
