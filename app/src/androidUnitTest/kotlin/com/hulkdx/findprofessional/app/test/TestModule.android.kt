package com.hulkdx.findprofessional.app.test

import com.hulkdx.findprofessional.core.config.PlatformSpecific as PlatformSpecific1

actual fun testPlatformSpecific(): PlatformSpecific1 {
    return object : PlatformSpecific1 {
        override fun isDebug() = true
        override fun localhostUrl() = ""
        override fun appDirectoryPath() = ""
    }
}
