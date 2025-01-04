package com.hulkdx.findprofessional.core.utils

import kotlinx.datetime.Clock
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

interface ClockProvider {
    fun clock(): Clock
}

class ClockProviderImpl : ClockProvider {
    override fun clock(): Clock = Clock.System
}

val clockModule
    get() = module {
        factoryOf(::ClockProviderImpl) bind ClockProvider::class
    }