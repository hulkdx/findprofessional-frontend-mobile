package com.hulkdx.findprofessional.app.di

import com.hulkdx.findprofessional.app.di.module.setupApiInterceptors
import com.hulkdx.findprofessional.feature.developer.loadMockDataOnDebug
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(platformModule: Module) {
    startKoin {
        modules(platformModule)
        modules(appModule)
    }
    setupApiInterceptors()
    loadMockDataOnDebug()
}
