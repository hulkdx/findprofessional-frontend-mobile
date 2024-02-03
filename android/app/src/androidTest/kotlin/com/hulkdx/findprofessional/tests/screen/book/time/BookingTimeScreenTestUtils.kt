package com.hulkdx.findprofessional.tests.screen.book.time

import androidx.compose.ui.test.performClick
import com.hulkdx.findprofessional.InMemoryApi
import com.hulkdx.findprofessional.resources.MR
import com.hulkdx.findprofessional.tests.screen.home.detail.launchHomeDetailScreen
import com.hulkdx.findprofessional.utils.Rule
import com.hulkdx.findprofessional.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.utils.onNodeWithTextRes


fun launchBookingTimeScreen(
    rule: Rule,
    email: String = "test@email.com",
    password: String = "somepass",
    block: BookingTimeScreenDsl.() -> Unit,
): BookingTimeScreenDsl {
    InMemoryApi.setUser(email, password)

    launchHomeDetailScreen(rule) {
        pressBookButton()
    }.verify {
        bookScreenShown()
    }

    return BookingTimeScreenDsl(rule).apply(block)
}


class BookingTimeScreenDsl(
    private val rule: Rule,
) {

    fun pressContinue() {
        rule.onNodeWithTextRes(MR.strings.continue1.resourceId)
            .performClick()
    }

    fun verify(block: BookingTimeScreenVerify.() -> Unit) =
        BookingTimeScreenVerify(rule).apply(block)
}

class BookingTimeScreenVerify(
    private val rule: Rule,
) {
    fun bookingSummeryShown() {
        rule.assertNodeIsDisplayed("BookingSummeryScreen")
    }
}
