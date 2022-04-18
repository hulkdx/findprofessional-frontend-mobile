package com.hulkdx.findprofessional.feature.authentication.signup

import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.feature.authentication.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val signUpModule: Module
    get() = module {
        factory { SignUpNavigationScreen() } bind NavigationScreen::class

        viewModel { SignUpViewModel() }
    }

