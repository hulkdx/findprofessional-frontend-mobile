package com.hulkdx.findprofessional.app.test

import androidx.test.platform.app.InstrumentationRegistry
import com.hulkdx.findprofessional.core.config.PlatformSpecific

actual fun testPlatformSpecific(): PlatformSpecific {
    return TestPlatformSpecificAndroid()
}

private class TestPlatformSpecificAndroid : PlatformSpecific {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    override fun isDebug() = com.hulkdx.findprofessional.core.config.isDebug()

    override fun localhostUrl() = "10.0.2.2"

    override fun appDirectoryPath(): String {
        return context.filesDir.absolutePath
    }
}
