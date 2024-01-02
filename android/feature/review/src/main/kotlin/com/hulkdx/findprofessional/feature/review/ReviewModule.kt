package com.hulkdx.findprofessional.feature.review

import com.hulkdx.findprofessional.feature.navigation.screen.AndroidNavigationScreen
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module


val reviewModule: Module
    get() = module {
        factoryOf(::ReviewNavigationScreen) bind AndroidNavigationScreen::class
        viewModelOf(::ReviewViewModel)
    }
