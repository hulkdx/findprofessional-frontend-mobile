package com.hulkdx.findprofessional.core.platform

import kotlin.system.exitProcess

actual fun isDebug(): Boolean {
    return runCatching {
        val clazz = Class.forName("com.hulkdx.findprofessional.app.AndroidIsDebug")
        val instance = clazz.getField("INSTANCE").get(null)
        val method = clazz.getMethod("isDebug")
        method.invoke(instance) as Boolean
    }.getOrDefault(false)
}

actual fun closeApp() {
    exitProcess(0)
}
