package com.hulkdx.findprofessional.common.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration


fun startKoinCommon(
    appDeclaration: KoinAppDeclaration
) {
    startKoin {
        appDeclaration()
        modules(commonModule)
    }
}
