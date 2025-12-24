package com.hulkdx.findprofessional.app.di

import com.hulkdx.findprofessional.app.di.module.apiModule
import com.hulkdx.findprofessional.app.di.module.debugModules
import com.hulkdx.findprofessional.app.di.module.navigationModule
import com.hulkdx.findprofessional.core.storage.datastoreModule
import com.hulkdx.findprofessional.core.utils.clockModule
import com.hulkdx.findprofessional.feature.authentication.login.loginModule
import com.hulkdx.findprofessional.feature.authentication.signup.signUpModule
import com.hulkdx.findprofessional.feature.authentication.splash.splashModule
import com.hulkdx.findprofessional.feature.booking.bookModule
import com.hulkdx.findprofessional.feature.home.homeModule
import com.hulkdx.findprofessional.feature.mybookings.myBookingsModule
import com.hulkdx.findprofessional.feature.pro.auth.signup.signUpProModule
import com.hulkdx.findprofessional.feature.pro.availability.proAvailabilityModule
import com.hulkdx.findprofessional.feature.pro.profile.proProfileModule
import com.hulkdx.findprofessional.feature.pro.schedule.proScheduleModule
import com.hulkdx.findprofessional.feature.profile.profileModule
import com.hulkdx.findprofessional.feature.review.reviewModule
import com.hulkdx.findprofessional.libs.navigation.decompose.decomposeModule
import org.koin.core.module.Module

val appModule: List<Module>
    get() = listOf(
        *debugModules(),
        apiModule,
        datastoreModule,
        decomposeModule,
        clockModule,
        navigationModule,

        splashModule,
        loginModule,
        signUpModule,
        homeModule,
        reviewModule,
        profileModule,
        bookModule,
        myBookingsModule,

        signUpProModule,
        proScheduleModule,
        proProfileModule,
        proAvailabilityModule,
    )
