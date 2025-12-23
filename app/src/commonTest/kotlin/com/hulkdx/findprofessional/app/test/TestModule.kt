package com.hulkdx.findprofessional.app.test

import com.hulkdx.findprofessional.feature.pro.usecase.GetAvailabilityUseCase
import com.hulkdx.findprofessional.core.platform.PlatformSpecific
import com.hulkdx.findprofessional.core.utils.ClockProvider
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.time.Clock
import kotlin.time.Instant

val testModule
    get() = module {
        factory { testPlatformSpecific() } bind PlatformSpecific::class
        single { TestClockProvider } bind ClockProvider::class

        // Singlaton modules that needs to be reset for each test add it here:
        singleOf(::GetAvailabilityUseCase)
    }

expect fun testPlatformSpecific(): PlatformSpecific

object TestClockProvider : ClockProvider {
    private var testClock: Clock = Clock.System

    override fun clock() = testClock
    override fun defaultTimeZone() = TimeZone.UTC

    fun setNow(now: Instant) {
        testClock = object : Clock {
            override fun now() = now
        }
    }

    fun setNow(now: LocalDate, timeZone: TimeZone = TimeZone.UTC) {
        val instant = now.atStartOfDayIn(timeZone)
        setNow(instant)
    }

    fun reset() {
        testClock = Clock.System
    }
}
