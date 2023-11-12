package com.hulkdx.findprofessional.common.di

import com.hulkdx.findprofessional.common.config.PlatformSpecific
import com.hulkdx.findprofessional.common.config.PlatformSpecificIOS
import com.hulkdx.findprofessional.common.navigation.Navigator
import org.koin.core.definition.Definition
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module


fun initKoinIOS(navigator: Definition<Navigator>) {
    initKoin {
        modules(
            appModuleIOS(navigator),
        )
    }
}

fun appModuleIOS(navigator: Definition<Navigator>) = module {
    factoryOf(::PlatformSpecificIOS) bind PlatformSpecific::class
    single(definition = navigator)
}
