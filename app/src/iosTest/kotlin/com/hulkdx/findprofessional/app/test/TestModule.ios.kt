package com.hulkdx.findprofessional.app.test

import com.hulkdx.findprofessional.core.config.PlatformSpecific
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual fun testPlatformSpecific(): PlatformSpecific {
    return TestPlatformSpecificIOS()
}

private class TestPlatformSpecificIOS : PlatformSpecific {

    override fun isDebug() = com.hulkdx.findprofessional.core.config.isDebug()

    override fun localhostUrl() = "localhost"

    override fun appDirectoryPath(): String {
        @OptIn(ExperimentalForeignApi::class)
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(documentDirectory?.path)
    }
}
