package com.hulkdx.findprofessional.core.config

import android.content.Context

class PlatformSpecificAndroid(
    private val appContext: Context,
) : PlatformSpecific {

    override fun localhostUrl() = "10.0.2.2"

    override fun appDirectoryPath(): String {
        return appContext.filesDir.absolutePath
    }
}

actual fun isDebug(): Boolean {
    return true
}
