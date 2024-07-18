package com.hulkdx.findprofessional.app.test

import com.hulkdx.findprofessional.core.config.PlatformSpecific

actual fun testPlatformSpecific(): PlatformSpecific {
    return object : PlatformSpecific {
        override fun isDebug() = true
        override fun localhostUrl() = ""
        override fun appDirectoryPath() = ""
    }
}
