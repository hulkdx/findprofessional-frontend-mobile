package com.hulkdx.findprofessional.app.di.module

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.outlined.AccountCircle
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.availability
import com.hulkdx.findprofessional.core.resources.bookings
import com.hulkdx.findprofessional.core.resources.explorer
import com.hulkdx.findprofessional.core.resources.ic_nav_bookings
import com.hulkdx.findprofessional.core.resources.ic_nav_explorer
import com.hulkdx.findprofessional.core.resources.ic_nav_profile
import com.hulkdx.findprofessional.core.resources.profile
import com.hulkdx.findprofessional.core.resources.schedule
import com.hulkdx.findprofessional.core.ui.commonui.navbar.AppNavBars
import com.hulkdx.findprofessional.core.ui.commonui.navbar.NavBarsItem
import com.hulkdx.findprofessional.core.ui.commonui.navbar.ProAppNavBars
import com.hulkdx.findprofessional.feature.home.HomeNavigationScreen
import com.hulkdx.findprofessional.feature.pro.availability.ProAvailabilityNavigationScreen
import com.hulkdx.findprofessional.feature.pro.profile.ProProfileNavigationScreen
import com.hulkdx.findprofessional.feature.pro.schedule.ProScheduleNavigationScreen
import com.hulkdx.findprofessional.feature.profile.ProfileNavigationScreen
import org.koin.core.module.Module
import org.koin.dsl.module


val navigationModule: Module
    get() = module {
        single {
            AppNavBars(
                listOf(
                    NavBarsItem(
                        text = Res.string.explorer,
                        icon = Res.drawable.ic_nav_explorer,
                        screen = HomeNavigationScreen.Home(),
                    ),
                    NavBarsItem(
                        text = Res.string.bookings,
                        icon = Res.drawable.ic_nav_bookings,
                        screen = ProfileNavigationScreen.Main,
                    ),
                    NavBarsItem(
                        text = Res.string.profile,
                        icon = Res.drawable.ic_nav_profile,
                        screen = ProfileNavigationScreen.Main,
                    ),
                )
            )
        }

        single {
            ProAppNavBars(
                listOf(
                    NavBarsItem(
                        text = Res.string.schedule,
                        iconVector = Icons.Filled.Event,
                        screen = ProScheduleNavigationScreen,
                    ),
                    NavBarsItem(
                        text = Res.string.availability,
                        iconVector = Icons.Filled.AccessTime,
                        screen = ProAvailabilityNavigationScreen.Main,
                    ),
                    NavBarsItem(
                        text = Res.string.profile,
                        iconVector = Icons.Outlined.AccountCircle,
                        screen = ProProfileNavigationScreen.Main,
                    ),
                )
            )
        }
    }
