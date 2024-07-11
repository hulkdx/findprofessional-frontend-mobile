package com.hulkdx.findprofessional.app.di

import com.hulkdx.findprofessional.app.di.module.apiModule
import com.hulkdx.findprofessional.core.storage.datastoreModule
import com.hulkdx.findprofessional.feature.authentication.login.loginModule
import com.hulkdx.findprofessional.feature.authentication.splash.splashModule
import com.hulkdx.findprofessional.libs.navigation.decompose.decomposeModule

val appModule
    get() = listOf(
        apiModule,
        datastoreModule,
        decomposeModule,

        splashModule,
        loginModule,
    )
