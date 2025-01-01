package com.hulkdx.findprofessional.app.test

import com.hulkdx.findprofessional.core.platform.PlatformSpecific
import org.koin.dsl.bind
import org.koin.dsl.module

val testModule
    get() = module {
        factory { testPlatformSpecific() } bind PlatformSpecific::class
    }

expect fun testPlatformSpecific(): PlatformSpecific
