package com.hulkdx.findprofessional.feature.book

import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryNavigationScreen
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryViewModel
import com.hulkdx.findprofessional.feature.book.time.BookingTimeNavigationScreen
import com.hulkdx.findprofessional.feature.book.time.BookingTimeViewModel
import com.hulkdx.findprofessional.feature.navigation.screen.AndroidNavigationScreen
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module


val bookModule: Module
    get() = module {
        factoryOf(::BookingTimeNavigationScreen) bind AndroidNavigationScreen::class
        viewModelOf(::BookingTimeViewModel)

        factoryOf(::BookingSummeryNavigationScreen) bind AndroidNavigationScreen::class
        viewModelOf(::BookingSummeryViewModel)
    }
