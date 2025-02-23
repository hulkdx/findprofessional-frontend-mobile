@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.book.summery

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import com.hulkdx.findprofessional.app.test.screen.book.time.launchBookingTimeScreen
import com.hulkdx.findprofessional.app.test.utils.Rule
import com.hulkdx.findprofessional.app.test.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.app.test.utils.setUser
import com.hulkdx.findprofessional.app.test.utils.waitUntilAppearText


fun launchBookingSummeryScreen(
    rule: Rule,
    email: String = "test@email.com",
    password: String = "somepass",
    block: BookingSummeryScreenDsl.() -> Unit,
): BookingSummeryScreenDsl {
    setUser(email, password)

    launchBookingTimeScreen(rule) {
        pressContinue()
    }.verify {
        bookingSummeryShown()
    }

    return BookingSummeryScreenDsl(rule).apply(block)
}


class BookingSummeryScreenDsl(
    private val rule: Rule,
) {
    fun pressSkypeId() {
        rule.onNodeWithText("Your Skype ID")
            .performClick()
    }

    fun typeSkypeIdInProfileScreen(txt: String) {
        rule.onNodeWithText("Skype ID")
            .performTextInput(txt)
    }

    fun pressSaveInProfileScreen() {
        rule.onNodeWithText("Save")
            .performClick()
    }

    fun verify(block: BookingSummeryScreenVerify.() -> Unit) =
        BookingSummeryScreenVerify(rule).apply(block)
}

class BookingSummeryScreenVerify(
    private val rule: Rule,
) {
    fun profileEditScreenShown() {
        rule.assertNodeIsDisplayed("ProfileEditScreen")
    }

    fun bookingSummeryShown() {
        rule.assertNodeIsDisplayed("BookingSummeryScreen")
    }

    fun skypeIdIs(txt: String) {
        rule.waitUntilAppearText(txt)
        rule.onNodeWithText(txt)
            .assertIsDisplayed()
    }

    fun then(block: BookingSummeryScreenDsl.() -> Unit) = BookingSummeryScreenDsl(rule).apply(block)
}
