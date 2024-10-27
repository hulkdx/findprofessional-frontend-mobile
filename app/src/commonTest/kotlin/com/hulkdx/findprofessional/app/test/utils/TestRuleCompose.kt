@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.utils

import androidx.compose.ui.test.ComposeTimeoutException
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performScrollToNode

typealias Rule = ComposeUiTest

fun Rule.waitUntilAppear(
    testTag: String,
    timeoutMillis: Long = 10_000,
) {
    try {
        waitUntil(timeoutMillis = timeoutMillis) {
            onAllNodesWithTag(testTag, useUnmergedTree = true).fetchSemanticsNodes().size == 1
        }
    } catch (e: ComposeTimeoutException) {
        throw RuntimeException("cannot find a node with test tag : $testTag after 10 seconds.")
    }
}

fun Rule.waitUntilAppearText(
    text: String,
    timeoutMillis: Long = 10_000,
) {
    try {
        waitUntil(timeoutMillis = timeoutMillis) {
            onAllNodesWithText(text, useUnmergedTree = true).fetchSemanticsNodes().size == 1
        }
    } catch (e: ComposeTimeoutException) {
        throw RuntimeException("cannot find a node with text : $text after 10 seconds.")
    }
}

fun Rule.assertNodeIsDisplayed(testTag: String) {
    waitUntilAppear(testTag = testTag)
    onNodeWithTag(testTag)
        .assertIsDisplayed()
}

fun Rule.assertAppIsClosed(timeoutMillis: Long = 10_000) {
    try {
        waitUntil(timeoutMillis = timeoutMillis) {
            runCatching { onRoot().assertDoesNotExist() }.isSuccess
        }
    } catch (e: ComposeTimeoutException) {
        throw RuntimeException("The app is not closed after 10 seconds.")
    }
}

fun Rule.lazyColumnScrollTo(
    lazyColumnTestTag: String,
    nodeTestTag: String,
): SemanticsNodeInteraction {
    onNodeWithTag(lazyColumnTestTag)
        .assertIsDisplayed()
        .performScrollToNode(hasTestTag(nodeTestTag))
    return onNodeWithTag("showAllReviews")
}
