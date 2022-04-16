package com.hulkdx.findprofessional.common.di

import com.hulkdx.findprofessional.common.feature.authentication.login.LoginRepository
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val commonModule: Module
    get() = module {
        factory { LoginRepository() }
    }

