package com.hulkdx.findprofessional.tests.screen.book.time

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
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
        rule.onNodeWithTag("BookingTimeScreen.LazyColumn")
            .assertIsDisplayed()
            .performScrollToNode(hasText(time.toText()))

        rule.onNodeWithText(time.toText())
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
            .assert(
                hasTestTag(BookingTimeUiState.BookingTime.Type.Selected.name)
            )
    }
}

private fun ProfessionalAvailability.toText(): String {
    val startTime = formattedTime(from.toMinutesOfDay())
    val endTime = formattedTime(to.toMinutesOfDay())
    return "$startTime - $endTime"
}
