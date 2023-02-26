package com.hulkdx.findprofessional.common.config

import org.koin.mp.KoinPlatformTools

interface PlatformSpecific {
    fun isDebug(): Boolean
    fun localhostUrl(): String
    fun appDirectoryPath(): String
}

fun getPlatformSpecific(): PlatformSpecific =
    KoinPlatformTools.defaultContext().get().get()
