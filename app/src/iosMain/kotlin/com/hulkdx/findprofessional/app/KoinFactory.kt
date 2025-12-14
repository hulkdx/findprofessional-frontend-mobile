package com.hulkdx.findprofessional.app

import com.hulkdx.findprofessional.app.di.initKoin
import com.hulkdx.findprofessional.core.platform.PlatformSpecific
import com.hulkdx.findprofessional.core.platform.PlatformSpecificIOS
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.mp.KoinPlatformTools

@Suppress("unused") // used in swift
class KoinFactoryIos {
    fun initKoin(swiftModules: Module.() -> Unit) {
        initKoin(iOSModule(swiftModules))
    }
}

private fun iOSModule(swiftModules: Module.() -> Unit) = module {
    factoryOf(::PlatformSpecificIOS) bind PlatformSpecific::class
    swiftModules(this)
}

inline fun <reified T : Any> get() = KoinPlatformTools.defaultContext().get().get<T>()
