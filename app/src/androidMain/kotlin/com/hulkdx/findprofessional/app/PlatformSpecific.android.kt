package com.hulkdx.findprofessional.app

import android.content.Context
import com.hulkdx.findprofessional.core.platform.PlatformSpecific

class PlatformSpecificAndroid(
    private val appContext: Context,
) : PlatformSpecific {

    override fun localhostUrl() = "10.0.2.2"

    override fun appDirectoryPath(): String {
        return appContext.filesDir.absolutePath
    }
}
