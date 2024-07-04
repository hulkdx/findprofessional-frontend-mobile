package com.hulkdx.findprofessional.core.utils

import android.app.Activity
import android.content.Context


object ApplicationUtils {
    fun restartApplication(context: Context) {
        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        context.startActivity(intent)
        (context as Activity).finish()
        Runtime.getRuntime().exit(0)
    }
}
