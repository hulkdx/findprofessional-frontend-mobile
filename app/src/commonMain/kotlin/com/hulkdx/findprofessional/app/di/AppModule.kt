package com.hulkdx.findprofessional.app.di

import com.hulkdx.findprofessional.app.di.module.apiModule
import com.hulkdx.findprofessional.app.di.module.debugModules
import com.hulkdx.findprofessional.core.storage.datastoreModule
import com.hulkdx.findprofessional.feature.authentication.login.loginModule
import com.hulkdx.findprofessional.feature.authentication.signup.signUpModule
import com.hulkdx.findprofessional.feature.authentication.splash.splashModule
import com.hulkdx.findprofessional.feature.book.bookModule
import com.hulkdx.findprofessional.feature.home.homeModule
import com.hulkdx.findprofessional.feature.pro.auth.signup.signUpProModule
import com.hulkdx.findprofessional.feature.pro.availability.proAvailabilityModule
import com.hulkdx.findprofessional.feature.pro.profile.proProfileModule
import com.hulkdx.findprofessional.feature.pro.schedule.proScheduleModule
import com.hulkdx.findprofessional.feature.profile.profileModule
import com.hulkdx.findprofessional.feature.review.reviewModule
import com.hulkdx.findprofessional.libs.navigation.decompose.decomposeModule
import org.koin.core.module.Module

val appModule: List<Module>
    get() =
        // @formatter:off
        debugModules() +
        listOf(
            apiModule,
            datastoreModule,
            decomposeModule,

            splashModule,
            loginModule,
            signUpModule,
            homeModule,
            reviewModule,
            profileModule,
            bookModule,

            signUpProModule,
            proScheduleModule,
            proProfileModule,
            proAvailabilityModule,
        )
        // @formatter:on
