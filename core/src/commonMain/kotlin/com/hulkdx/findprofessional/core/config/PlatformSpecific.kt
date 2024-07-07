package com.hulkdx.findprofessional.core.config


interface PlatformSpecific {
    fun isDebug(): Boolean
    fun localhostUrl(): String
    fun appDirectoryPath(): String
}

expect fun isDebug(): Boolean
