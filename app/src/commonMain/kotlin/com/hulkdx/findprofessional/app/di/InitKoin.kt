package com.hulkdx.findprofessional.app.di

import com.hulkdx.findprofessional.feature.authentication.splash.splashModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(platformModule: Module) {
    startKoin {
        modules(
            platformModule,
            splashModule,

            // TODO: fix
            testModule,
        )
    }
}
