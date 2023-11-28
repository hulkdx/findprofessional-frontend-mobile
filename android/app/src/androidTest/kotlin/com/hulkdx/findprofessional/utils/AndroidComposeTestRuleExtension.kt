package com.hulkdx.findprofessional.utils

import androidx.annotation.StringRes
import androidx.compose.ui.input.key.NativeKeyEvent
import androidx.compose.ui.test.ComposeTimeoutException
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.hulkdx.findprofessional.MainActivity
import org.hamcrest.CoreMatchers.anything

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
    try {
        waitUntil(timeoutMillis) {
            onAllNodesWithTag(testTag).fetchSemanticsNodes().size == 1
        }
    } catch (e: ComposeTimeoutException) {
        throw RuntimeException("cannot find a node with test tag : $testTag after 10 seconds.")
    }
}

fun Rule.assertNodeIsDisplayed(testTag: String) {
    waitUntilAppear(testTag = testTag)
    onNodeWithTag(testTag)
        .assertIsDisplayed()
}

fun Rule.pressBackButton() {
    Espresso.pressBackUnconditionally()
}

fun Rule.assertAppIsClosed(timeoutMillis: Long = 10_000) {
    try {
        waitUntil(timeoutMillis) {
            runCatching { onRoot().assertDoesNotExist() }.isSuccess
        }
    } catch (e: ComposeTimeoutException) {
        throw RuntimeException("The app is not closed after 10 seconds.")
    }
}
