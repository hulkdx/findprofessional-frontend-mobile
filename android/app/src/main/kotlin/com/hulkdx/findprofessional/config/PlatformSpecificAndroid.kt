package com.hulkdx.findprofessional.config

import android.content.Context
import com.hulkdx.findprofessional.common.BuildConfig
import com.hulkdx.findprofessional.common.config.PlatformSpecific

class PlatformSpecificAndroid(
    private val appContext: Context,
) : PlatformSpecific {

    override fun isDebug() = BuildConfig.DEBUG

    override fun localhostUrl() = "10.0.2.2"

    override fun appDirectoryPath(): String {
        return appContext.filesDir.absolutePath
    }
}
