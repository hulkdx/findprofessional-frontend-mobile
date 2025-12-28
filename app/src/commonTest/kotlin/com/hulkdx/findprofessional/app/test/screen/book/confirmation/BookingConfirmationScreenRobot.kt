@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.book.confirmation

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hulkdx.findprofessional.app.test.utils.Rule
import com.hulkdx.findprofessional.app.test.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.app.test.utils.get
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.feature.booking.BookingNavigationScreen

fun launchBookingConfirmationScreen(
    rule: Rule,
    block: BookingConfirmationScreenDsl.() -> Unit,
): BookingConfirmationScreenDsl {
    rule.assertNodeIsDisplayed("LoginScreen")

    val navigator: Navigator = get()
    navigator.navigate(BookingNavigationScreen.Confirmation)

    rule.assertNodeIsDisplayed("BookingConfirmationScreen")

    return BookingConfirmationScreenDsl(rule).apply(block)
}

class BookingConfirmationScreenDsl(
    private val rule: Rule,
) {
    fun pressClose() {
        rule.onNodeWithText("Close")
            .performClick()
    }

    fun verify(block: BookingConfirmationScreenVerify.() -> Unit) =
        BookingConfirmationScreenVerify(rule).apply(block)
}

class BookingConfirmationScreenVerify(
    private val rule: Rule,
) {
    fun bookingConfirmationShown() {
        rule.assertNodeIsDisplayed("BookingConfirmationScreen")
    }

    fun homeScreenShown() {
        rule.assertNodeIsDisplayed("HomeScreen")
    }
}
