package com.hulkdx.findprofessional.core.di

import org.koin.core.context.startKoin


fun initKoin() {
    startKoin {
        modules(appModule())
    }
}
