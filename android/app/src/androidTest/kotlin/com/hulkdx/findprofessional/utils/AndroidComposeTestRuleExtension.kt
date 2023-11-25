package com.hulkdx.findprofessional.utils

import androidx.annotation.StringRes
import androidx.compose.ui.test.ComposeTimeoutException
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.hulkdx.findprofessional.MainActivity

typealias Rule = AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>

fun AndroidComposeTestRule<*, *>.onNodeWithTextRes(
    @StringRes res: Int,
    substring: Boolean = false,
    ignoreCase: Boolean = false,
    useUnmergedTree: Boolean = false,
): SemanticsNodeInteraction {
    return onNodeWithText(activity.getString(res), substring, ignoreCase, useUnmergedTree)
}

fun AndroidComposeTestRule<*, *>.onNodeWithTagRes(@StringRes res: Int): SemanticsNodeInteraction {
    return onNodeWithTag(activity.getString(res))
}

fun Rule.waitUntilAppear(
    testTag: String,
    timeoutMillis: Long = 10_000,
) {
    waitUntil(timeoutMillis) {
        onAllNodesWithTag(testTag).fetchSemanticsNodes().size == 1
    }
}

fun Rule.assertNodeIsDisplayed(testTag: String) {
    waitUntilAppear(testTag = testTag)
    onNodeWithTag(testTag)
        .assertIsDisplayed()
}

@Suppress("UnusedReceiverParameter")
fun Rule.pressBackButton() {
    // Minor: Find a way to do this with jetpack compose test
    Espresso.pressBackUnconditionally()
}

fun Rule.assertAppIsClosed(timeoutMillis: Long = 10_000) {
    val startTime = System.currentTimeMillis()
    while (true) {
        try {
            onRoot().assertDoesNotExist()
            break
        } catch (e: AssertionError) {
            Thread.sleep(100)
            if (System.currentTimeMillis() - startTime > timeoutMillis) {
                throw ComposeTimeoutException(
                    "Condition still not satisfied after $timeoutMillis ms"
                )
            }
        }
    }
}
