package com.hulkdx.findprofessional.app.test

import com.hulkdx.findprofessional.core.config.PlatformSpecific
import org.koin.dsl.bind
import org.koin.dsl.module

val testModule
    get() = module {
        factory { TestPlatformSpecific() } bind PlatformSpecific::class
    }


class TestPlatformSpecific : PlatformSpecific {
    override fun isDebug() = true
    override fun localhostUrl() = ""
    override fun appDirectoryPath() = ""
}
