package com.hulkdx.findprofessional.app.di

import com.hulkdx.findprofessional.app.di.module.apiModule
import com.hulkdx.findprofessional.app.di.module.debugModule
import com.hulkdx.findprofessional.core.storage.datastoreModule
import com.hulkdx.findprofessional.feature.authentication.login.loginModule
import com.hulkdx.findprofessional.feature.authentication.signup.signUpModule
import com.hulkdx.findprofessional.feature.authentication.splash.splashModule
import com.hulkdx.findprofessional.feature.home.homeModule
import com.hulkdx.findprofessional.libs.navigation.decompose.decomposeModule
import org.koin.core.module.Module

val appModule: List<Module>
    get() = listOf(
        debugModule(),
        apiModule,
        datastoreModule,
        decomposeModule,

        splashModule,
        loginModule,
        signUpModule,
        homeModule,
    )
