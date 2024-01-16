package com.hulkdx.findprofessional.common.di

import com.hulkdx.findprofessional.common.config.storage.datastore.datastoreModule
import com.hulkdx.findprofessional.common.di.module.apiModule
import com.hulkdx.findprofessional.common.di.module.setupApiInterceptors
import com.hulkdx.findprofessional.common.feature.authentication.login.loginModule
import com.hulkdx.findprofessional.common.feature.authentication.logout.logoutModule
import com.hulkdx.findprofessional.common.feature.authentication.signup.signUpModule
import com.hulkdx.findprofessional.common.feature.home.homeModule
import com.hulkdx.findprofessional.common.feature.review.reviewModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    appDeclaration: KoinAppDeclaration,
) {
    startKoin {
        appDeclaration()
        modules(
            apiModule +
                    datastoreModule +

                    allModules()
        )
    }

    setupApiInterceptors()
}

fun allModules() = listOf(
    loginModule,
    signUpModule,
    logoutModule,
    homeModule,
    reviewModule,
)
