package com.hulkdx.findprofessional.utils

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.Bitmap.CompressFormat.PNG
import java.io.File
import java.io.FileOutputStream


object ScreenshotUtils {

    fun take(
        bitmap: Bitmap,
        screenshotsDir: String,
        screenshotName: String,
        outputCompressFormat: CompressFormat = PNG,
        outputCompressQuality: Int = 100,
    ) {
        val dir = File(screenshotsDir)
        createDirIfNotExists(dir)

        val screenshotPath = "$screenshotsDir/$screenshotName"
        val file = File(screenshotPath)
        createFileIfNotExists(file)

        FileOutputStream(screenshotPath).use { out ->
            bitmap.compress(outputCompressFormat, outputCompressQuality, out)
        }
    }

    private fun createFileIfNotExists(file: File) {
        if (!file.exists()) {
            file.createNewFile()
        }
    }

    private fun createDirIfNotExists(dir: File) {
        if (!dir.exists()) {
            dir.mkdirs()
        }
    }
}
