package com.hulkdx.findprofessional.common.di

import com.hulkdx.findprofessional.common.di.module.apiModule
import com.hulkdx.findprofessional.common.feature.authentication.login.loginModule
import com.hulkdx.findprofessional.common.feature.authentication.signup.signUpModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    appDeclaration: KoinAppDeclaration
) {
    startKoin {
        appDeclaration()
        modules(
            apiModule,
            platformModule(),

            loginModule,
            signUpModule,
        )
    }
}
