package com.hulkdx.findprofessional.core.platform


/**
 *  Platform configurations that are different in each platform.
 */
interface PlatformSpecific {
    fun isDebug(): Boolean
    fun localhostUrl(): String
    fun appDirectoryPath(): String
}

expect fun isDebug(): Boolean

expect fun closeApp()
