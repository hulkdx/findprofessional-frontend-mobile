package com.hulkdx.findprofessional.app.di

import com.hulkdx.findprofessional.app.di.module.apiModule
import com.hulkdx.findprofessional.app.di.module.setupApiInterceptors
import com.hulkdx.findprofessional.common.config.storage.datastore.datastoreModule
import com.hulkdx.findprofessional.common.feature.authentication.login.loginModule
import com.hulkdx.findprofessional.common.feature.authentication.logout.logoutModule
import com.hulkdx.findprofessional.common.feature.authentication.signup.signUpModule
import com.hulkdx.findprofessional.common.feature.book.bookModule
import com.hulkdx.findprofessional.common.feature.home.homeModule
import com.hulkdx.findprofessional.common.feature.review.reviewModule
import com.hulkdx.findprofessional.feature.authentication.splash.splashModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            // @formatter:off
            apiModule +
            datastoreModule +
            allModules()
            // @formatter:on
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
    bookModule,
    splashModule,
)
