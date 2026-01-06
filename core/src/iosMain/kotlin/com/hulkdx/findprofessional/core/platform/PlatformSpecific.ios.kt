package com.hulkdx.findprofessional.core.platform

import platform.posix.exit
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
actual fun isDebug(): Boolean {
    return Platform.isDebugBinary
}

actual fun closeApp() {
    exit(0)
}
