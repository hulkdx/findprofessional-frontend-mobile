package com.hulkdx.findprofessional.app

import com.hulkdx.findprofessional.app.di.initKoin
import com.hulkdx.findprofessional.core.config.PlatformSpecific
import com.hulkdx.findprofessional.core.config.PlatformSpecificIOS
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module


class KoinFactoryIos {
    fun initKoin() {
        initKoin(iOSModule)
    }
}

private val iOSModule: Module
    get() = module {
        factoryOf(::PlatformSpecificIOS) bind PlatformSpecific::class
    }
