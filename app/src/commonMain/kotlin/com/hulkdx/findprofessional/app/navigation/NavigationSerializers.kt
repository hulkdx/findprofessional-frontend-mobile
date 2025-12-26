package com.hulkdx.findprofessional.app.navigation

import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.NavigationScreenSerializer
import com.hulkdx.findprofessional.feature.authentication.AuthNavigationScreen
import com.hulkdx.findprofessional.feature.booking.BookingNavigationScreen
import com.hulkdx.findprofessional.feature.developer.DeveloperNavigationScreen
import com.hulkdx.findprofessional.feature.home.HomeNavigationScreen
import com.hulkdx.findprofessional.feature.mybookings.MyBookingsNavigationScreen
import com.hulkdx.findprofessional.feature.pro.auth.ProAuthNavigationScreen
import com.hulkdx.findprofessional.feature.pro.availability.ProAvailabilityNavigationScreen
import com.hulkdx.findprofessional.feature.pro.profile.ProProfileNavigationScreen
import com.hulkdx.findprofessional.feature.pro.schedule.ProScheduleNavigationScreen
import com.hulkdx.findprofessional.feature.profile.ProfileNavigationScreen
import com.hulkdx.findprofessional.feature.review.ReviewNavigationScreen
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass


fun setupNavigationScreenPolymorphicSerializable() {
    val navigationSerializersModule = SerializersModule {
        polymorphic(NavigationScreen::class) {
            subclass(DeveloperNavigationScreen::class)
            subclass(AuthNavigationScreen.Splash::class)
            subclass(AuthNavigationScreen.Login::class)
            subclass(AuthNavigationScreen.SignUp::class)
            subclass(HomeNavigationScreen.Home::class)
            subclass(HomeNavigationScreen.HomeDetail::class)
            subclass(ReviewNavigationScreen.Main::class)
            subclass(ProfileNavigationScreen.Main::class)
            subclass(ProfileNavigationScreen.Edit::class)
            subclass(BookingNavigationScreen.Time::class)
            subclass(BookingNavigationScreen.Summery::class)
            subclass(ProAuthNavigationScreen.SignUp::class)
            subclass(ProScheduleNavigationScreen::class)
            subclass(ProAvailabilityNavigationScreen.Main::class)
            subclass(ProAvailabilityNavigationScreen.Detail::class)
            subclass(ProProfileNavigationScreen.Main::class)
            subclass(ProProfileNavigationScreen.Edit::class)
            subclass(MyBookingsNavigationScreen.Main::class)
        }
    }

    val navigationJson = Json {
        serializersModule = navigationSerializersModule
        classDiscriminator = "type"
    }

    NavigationScreenSerializer.setJson(navigationJson)
}
