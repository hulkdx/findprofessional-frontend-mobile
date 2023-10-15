package com.hulkdx.findprofessional.feature.authentication.signup

import com.hulkdx.findprofessional.feature.navigation.screen.AndroidNavigationScreen
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val signUpModule: Module
    get() = module {
        factoryOf(::SignUpNavigationScreen) bind AndroidNavigationScreen::class
        viewModelOf(::SignUpViewModel)
    }
