package com.hulkdx.findprofessional.app.di

import com.hulkdx.findprofessional.feature.authentication.splash.splashModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            splashModule,

            // TODO: fix
            testModule,
        )
    }
}
