@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.book.time

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import androidx.compose.ui.unit.Dp
import com.hulkdx.findprofessional.app.test.screen.home.detail.launchHomeDetailScreen
import com.hulkdx.findprofessional.app.test.utils.Rule
import com.hulkdx.findprofessional.app.test.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.app.test.utils.setUser
import com.hulkdx.findprofessional.core.features.pro.ProfessionalAvailability
import com.hulkdx.findprofessional.core.utils.toMinutesOfDay
import com.hulkdx.findprofessional.feature.book.time.BookingTimeUiState.BookingTime.Type.Selected
import com.hulkdx.findprofessional.core.utils.TimeUtils.formattedTime


fun launchBookingTimeScreen(
    rule: Rule,
    email: String = "test@email.com",
    password: String = "somepass",
    block: BookingTimeScreenDsl.() -> Unit,
): BookingTimeScreenDsl {
    setUser(email, password)

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
        rule.onNodeWithText("Continue")
            .performClick()
    }

    fun pressTime(time: ProfessionalAvailability) {
        rule.onNodeWithTag("BookingTimeScreen.LazyColumn")
            .assertIsDisplayed()
            .performScrollToNode(hasText(time.toText()))
            .performTouchInput { swipeUp(bottom, bottom - (Dp(50F).toPx())) }

        rule.onNodeWithText(time.toText())
            .assertIsDisplayed()
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

    fun isHighlightedTime(time: ProfessionalAvailability) {
        rule.onNodeWithText(time.toText())
            .assert(hasTestTag(Selected.name))
    }
}

private fun ProfessionalAvailability.toText(): String {
    val startTime = formattedTime(from.toMinutesOfDay())
    val endTime = formattedTime(to.toMinutesOfDay())
    return "$startTime - $endTime"
}
