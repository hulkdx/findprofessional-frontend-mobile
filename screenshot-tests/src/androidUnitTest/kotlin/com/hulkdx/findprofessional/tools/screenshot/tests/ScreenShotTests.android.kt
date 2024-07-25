package com.hulkdx.findprofessional.tools.screenshot.tests

import androidx.compose.runtime.Composable
import app.cash.paparazzi.Paparazzi
import org.junit.runner.Description
import org.junit.runners.model.Statement

actual fun platformScreenShotTests(
    className: String,
    methodName: String,
    content: @Composable () -> Unit,
) {
    runPaparazziTest(className, methodName, content)
}


fun runPaparazziTest(
    className: String,
    methodName: String,
    content: @Composable () -> Unit,
) {
    val paparazzi = Paparazzi()

    val description = Description.createTestDescription(className, methodName)
    val statement = object : Statement() {
        override fun evaluate() {
            paparazzi.snapshot(composable = content)
        }
    }

    paparazzi.apply(statement, description).evaluate()
}
