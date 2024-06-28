package com.hulkdx.findprofessional.features.splash

import com.hulkdx.findprofessional.core.di.startDestinationRouteQualifier
import com.hulkdx.findprofessional.core.navigation.NavHostScreen
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val splashModule: Module
    get() = module {
        factory(startDestinationRouteQualifier) { SplashNavigationScreen().route }
        factoryOf(::SplashNavigationScreen) bind NavHostScreen::class
    }
