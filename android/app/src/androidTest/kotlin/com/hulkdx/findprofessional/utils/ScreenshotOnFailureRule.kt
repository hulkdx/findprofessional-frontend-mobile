package com.hulkdx.findprofessional.utils

import android.os.Environment
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice.getInstance
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.io.File

class ScreenshotOnFailureRule : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                try {
                    UiDeviceUtils.device = getInstance(InstrumentationRegistry.getInstrumentation())
                    base.evaluate()
                } catch (e: Throwable) {
                    runCatching { takeScreenshot(description.methodName) }
                    throw e
                }
            }
        }
    }

    companion object {

        fun takeScreenshot(fileName: String) {
            // TODO: api 29 fails on this:
            val dir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path

            val screenshotsDir = "$dir/uitest-screenshot-failure"
            val screenshotName = "$fileName.png"

            val dir1 = File(screenshotsDir)
            createDirIfNotExists(dir1)
            val file = File("$screenshotsDir/$screenshotName")
            createFileIfNotExists(file)

            UiDeviceUtils.device.takeScreenshot(file)
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
}
