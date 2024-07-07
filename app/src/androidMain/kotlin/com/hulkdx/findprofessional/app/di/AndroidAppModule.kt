package com.hulkdx.findprofessional.app.di

import com.hulkdx.findprofessional.core.config.PlatformSpecific
import com.hulkdx.findprofessional.core.config.PlatformSpecificAndroid
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module


val androidAppModule: Module
    get() = module {
        factoryOf(::PlatformSpecificAndroid) bind PlatformSpecific::class
    }
