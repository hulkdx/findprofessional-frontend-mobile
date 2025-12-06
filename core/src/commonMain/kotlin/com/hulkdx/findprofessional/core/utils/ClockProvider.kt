package com.hulkdx.findprofessional.core.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

interface ClockProvider {
    fun clock(): Clock
    fun defaultTimeZone(): TimeZone
}

class ClockProviderImpl : ClockProvider {
    override fun clock(): Clock = Clock.System
    override fun defaultTimeZone() = TimeZone.currentSystemDefault()
}

fun ClockProvider.localDateNow(): LocalDate {
    return clock().now().toLocalDateTime(defaultTimeZone()).date
}

val clockModule
    get() = module {
        factoryOf(::ClockProviderImpl) bind ClockProvider::class
    }
