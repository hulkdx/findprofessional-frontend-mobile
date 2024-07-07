package com.hulkdx.findprofessional.core.config


interface PlatformSpecific {
    fun localhostUrl(): String
    fun appDirectoryPath(): String
}

expect fun isDebug(): Boolean
