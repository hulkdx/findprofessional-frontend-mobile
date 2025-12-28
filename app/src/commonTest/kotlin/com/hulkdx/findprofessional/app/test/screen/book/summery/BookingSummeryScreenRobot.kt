@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.book.summery

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.test.screen.book.time.launchBookingTimeScreen
import com.hulkdx.findprofessional.app.test.utils.Rule
import com.hulkdx.findprofessional.app.test.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.app.test.utils.setUser


fun launchBookingSummeryScreen(
    rule: Rule,
    email: String = "test@email.com",
    password: String = "somepass",
    block: BookingSummeryScreenDsl.() -> Unit,
): BookingSummeryScreenDsl {
    setUser(email, password)

    launchBookingTimeScreen(rule) {
        pressAnyTime()
        pressContinue()
    }.verify {
        bookingSummeryShown()
    }

    return BookingSummeryScreenDsl(rule).apply(block)
}


class BookingSummeryScreenDsl(
    private val rule: Rule,
) {

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

    fun then(block: BookingSummeryScreenDsl.() -> Unit) = BookingSummeryScreenDsl(rule).apply(block)
}
