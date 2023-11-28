package com.hulkdx.findprofessional.utils

import android.os.Environment
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onRoot
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class ScreenshotOnFailureRule(
    private val composeRule: Rule,
) : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                try {
                    base.evaluate()
                } catch (e: Throwable) {
                    runCatching { takeScreenshotIfTestFails(description) }
                    throw e
                }
            }
        }
    }

    private fun takeScreenshotIfTestFails(description: Description) {
        // TODO: api 29 fails on this:
        val methodName = description.methodName
        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path

        // val bitmap = composeRule.onRoot().captureToImage().asAndroidBitmap()
        val screenshotsDir = "$dir/uitest-screenshot-failure"
        val screenshotName = "$methodName.png"
        // ScreenshotUtils.take(bitmap, screenshotsDir, screenshotName)

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        ScreenshotUtils.take(device, screenshotsDir, screenshotName)
    }
}
