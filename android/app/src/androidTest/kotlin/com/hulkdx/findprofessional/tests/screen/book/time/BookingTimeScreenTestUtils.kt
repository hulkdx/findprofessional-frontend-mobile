package com.hulkdx.findprofessional.tests.screen.book.time

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hulkdx.findprofessional.InMemoryApi
import com.hulkdx.findprofessional.common.feature.book.time.BookingTimeUiState
import com.hulkdx.findprofessional.common.feature.book.time.BookingTimeUtils.formattedTime
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability
import com.hulkdx.findprofessional.common.utils.toMinutesOfDay
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

    fun pressTime(time: ProfessionalAvailability) {
        rule.onNodeWithTime(time)
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
        rule.onNodeWithTime(time)
            .assert(
                hasTestTag(BookingTimeUiState.BookingTime.Type.Selected.name)
            )
    }
}

private fun Rule.onNodeWithTime(time: ProfessionalAvailability): SemanticsNodeInteraction {
    val startTime = formattedTime(time.from.toMinutesOfDay())
    val endTime = formattedTime(time.to.toMinutesOfDay())
    val text = "$startTime - $endTime"
    return onNodeWithText(text)
}
