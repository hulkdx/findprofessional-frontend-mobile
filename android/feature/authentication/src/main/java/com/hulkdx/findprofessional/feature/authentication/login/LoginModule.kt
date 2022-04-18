package com.hulkdx.findprofessional.feature.authentication.login

import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val loginModule: Module
    get() = module {
        factory { LoginNavigationScreen() } bind NavigationScreen::class

        viewModel { LoginViewModel(get(), get()) }
    }

