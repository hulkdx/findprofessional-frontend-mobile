package com.hulkdx.findprofessional.feature.authentication.signup

import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val signUpModule: Module
    get() = module {
        factoryOf(::SignUpNavigationScreen) bind NavigationScreen::class
        viewModelOf(::SignUpViewModel)
        factoryOf(::MainNavigationScreen) bind NavigationScreen::class
    }

