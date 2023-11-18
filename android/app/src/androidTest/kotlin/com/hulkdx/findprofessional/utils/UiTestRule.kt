package com.hulkdx.findprofessional.utils

import android.os.Environment
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onRoot
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.hulkdx.findprofessional.navigation.NavigatorImpl
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UiTestRule(
    private val composeRule: Rule,
) : TestRule, KoinComponent {

    private val navigator: NavigatorImpl by inject()
    private val dataStore: DataStore<Preferences> by inject()

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                try {
                    base.evaluate()
                } catch (e: Throwable) {
                    failed(description)
                    throw e
                }
            }
        }
    }


    private fun failed(description: Description) {
        runCatching {
            takeScreenshotIfTestFails(description)
        }
    }

    private fun takeScreenshotIfTestFails(description: Description) {
        val methodName = description.methodName
        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path

        val bitmap = composeRule.onRoot().captureToImage().asAndroidBitmap()
        val screenshotsDir = "$dir/uitest-screenshot-failure"
        val screenshotName = "$methodName.png"
        ScreenshotUtils.take(bitmap, screenshotsDir, screenshotName)
    }
}
