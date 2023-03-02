package com.hulkdx.findprofessional.utils

import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onRoot
import com.hulkdx.findprofessional.navigation.NavigatorImpl
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.java.KoinJavaComponent

class UiTestRule(
    private val composeRule: Rule
): TestRule {

    private val navigator by KoinJavaComponent.inject<NavigatorImpl>(NavigatorImpl::class.java)

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                try {
                    base.evaluate()
                } catch (e: Throwable) {
                    failed(description)
                    throw e
                } finally {
                    tearDown()
                }
            }
        }
    }

    private fun tearDown() {
        cleanup()
    }

    private fun cleanup() {
        navigator.screenState.value = null
    }

    private fun failed(description: Description) {
        takeScreenshotIfTestFails(description)
    }

    private fun takeScreenshotIfTestFails(description: Description) {
        val methodName = description.methodName
        val dataDir = composeRule.activity.filesDir.absolutePath

        val bitmap = composeRule.onRoot().captureToImage().asAndroidBitmap()
        val screenshotsDir = "$dataDir/uitest-screenshot-failure"
        val screenshotName = "$methodName.png"
        ScreenshotUtils.take(bitmap, screenshotsDir, screenshotName)
    }
}
