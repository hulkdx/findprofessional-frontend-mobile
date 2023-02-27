package com.hulkdx.findprofessional.common.config

import android.content.Context
import com.hulkdx.findprofessional.common.BuildConfig

class PlatformSpecificAndroid(
    private val appContext: Context,
) : PlatformSpecific {

    override fun isDebug() = BuildConfig.DEBUG

    override fun localhostUrl() = "10.0.2.2"

    override fun appDirectoryPath(): String {
        return appContext.filesDir.absolutePath
    }
}
