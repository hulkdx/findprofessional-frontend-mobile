package com.hulkdx.findprofessional.app.di

import com.hulkdx.findprofessional.core.storage.datastoreModule
import com.hulkdx.findprofessional.feature.authentication.login.loginModule
import com.hulkdx.findprofessional.feature.authentication.splash.splashModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(platformModule: Module) {
    startKoin {
        modules(
            platformModule,

            datastoreModule,

            splashModule,
            loginModule,

            // TODO: fix
            testModule,
        )
    }
}
