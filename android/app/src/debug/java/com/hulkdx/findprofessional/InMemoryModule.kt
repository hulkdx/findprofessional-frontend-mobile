package com.hulkdx.findprofessional

import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpApi
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import org.koin.core.module.Module
import org.koin.dsl.module

val inMemoryModule: Module
    get() = module {
        single<SignUpApi> { InMemoryApi.Signup }
    }
