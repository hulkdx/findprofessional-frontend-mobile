package com.hulkdx.findprofessional.common.config

class PlatformSpecificIOS : PlatformSpecific {
    override fun isDebug() = Platform.isDebugBinary
    override fun localhostUrl() = "localhost"
}
