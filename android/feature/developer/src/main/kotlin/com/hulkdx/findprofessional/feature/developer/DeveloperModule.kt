package com.hulkdx.findprofessional.feature.developer

import com.hulkdx.findprofessional.core.navigation.AndroidNavigationScreen
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val developerModule: Module
    get() = module {
        factoryOf(::DeveloperNavigationScreen) bind AndroidNavigationScreen::class
    }
