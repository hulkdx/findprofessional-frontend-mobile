package com.hulkdx.findprofessional.feature.home

import com.hulkdx.findprofessional.feature.navigation.screen.AndroidNavigationScreen
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeModule: Module
    get() = module {
        factoryOf(::HomeNavigationScreen) bind AndroidNavigationScreen::class
        viewModelOf(::HomeViewModel)
    }
