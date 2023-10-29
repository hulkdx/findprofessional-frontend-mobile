package com.hulkdx.findprofessional.common.config

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask
import kotlin.experimental.ExperimentalNativeApi

class PlatformSpecificIOS : PlatformSpecific {

    @OptIn(ExperimentalNativeApi::class)
    override fun isDebug() = Platform.isDebugBinary

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
