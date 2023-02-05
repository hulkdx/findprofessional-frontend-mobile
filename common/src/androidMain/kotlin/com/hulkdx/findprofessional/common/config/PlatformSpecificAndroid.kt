package com.hulkdx.findprofessional.common.config

import com.hulkdx.findprofessional.common.BuildConfig

class PlatformSpecificAndroid : PlatformSpecific {
    override fun isDebug() = BuildConfig.DEBUG

    override fun localhostUrl() = "10.0.2.2"
}
