package com.hulkdx.findprofessional.common.utils

import com.hulkdx.findprofessional.common.config.PlatformSpecific
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin as stopKoinOriginal
import org.koin.dsl.module


object KoinTestUtil {

    fun startKoin() {
        startKoin {
            modules(
                module {
                    single<PlatformSpecific> {
                        object : PlatformSpecific {
                            override fun isDebug(): Boolean {
                                return true
                            }

                            override fun localhostUrl(): String {
                                return ""
                            }

                            override fun appDirectoryPath(): String {
                                return ""
                            }
                        }
                    }
                }
            )
        }
    }

    fun stopKoin() {
        stopKoinOriginal()
    }
}
