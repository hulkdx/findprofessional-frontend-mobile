package com.hulkdx.findprofessional.feature.home.detail

import com.hulkdx.findprofessional.core.navigation.AndroidNavigationScreen
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module


val homeDetailModule: Module
    get() = module {
        factoryOf(::HomeDetailNavigationScreen) bind AndroidNavigationScreen::class
        viewModelOf(::HomeDetailViewModel)
    }
