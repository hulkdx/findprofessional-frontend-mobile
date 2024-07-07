package com.hulkdx.findprofessional.core.config

class PlatformSpecificAndroid(
    private val appContext: Context,
) : PlatformSpecific {

    override fun localhostUrl() = ""

    override fun appDirectoryPath(): String {
        return appContext.filesDir.absolutePath
    }
}

actual fun isDebug(): Boolean {
    return false
}
