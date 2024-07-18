package com.hulkdx.findprofessional.core.config

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask
import platform.posix.exit
import kotlin.experimental.ExperimentalNativeApi

class PlatformSpecificIOS : PlatformSpecific {

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

@OptIn(ExperimentalNativeApi::class)
actual fun isDebug(): Boolean {
    return Platform.isDebugBinary
}

actual fun closeApp() {
    exit(0)
}
