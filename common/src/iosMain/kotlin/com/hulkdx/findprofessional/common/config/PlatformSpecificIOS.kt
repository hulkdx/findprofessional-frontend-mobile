package com.hulkdx.findprofessional.common.config

import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

class PlatformSpecificIOS : PlatformSpecific {

    override fun isDebug() = Platform.isDebugBinary

    override fun localhostUrl() = "localhost"

    override fun appDirectoryPath(): String {
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
