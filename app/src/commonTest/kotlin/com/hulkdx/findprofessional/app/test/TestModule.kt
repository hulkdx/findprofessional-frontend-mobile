package com.hulkdx.findprofessional.app.test

import com.hulkdx.findprofessional.core.platform.PlatformSpecific
import com.hulkdx.findprofessional.core.utils.ClockProvider
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import org.koin.dsl.bind
import org.koin.dsl.module

val testModule
    get() = module {
        factory { testPlatformSpecific() } bind PlatformSpecific::class
        single { TestClockProvider } bind ClockProvider::class
    }

expect fun testPlatformSpecific(): PlatformSpecific

object TestClockProvider : ClockProvider {
    private var testClock: Clock = Clock.System

    override fun clock() = testClock

    fun setNow(now: Instant) {
        testClock = object: Clock {
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