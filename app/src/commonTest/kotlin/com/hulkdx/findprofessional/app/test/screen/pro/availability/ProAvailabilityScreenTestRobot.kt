@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.pro.availability

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.hulkdx.findprofessional.app.test.screen.login.launchLoginScreen
import com.hulkdx.findprofessional.app.test.utils.Rule
import com.hulkdx.findprofessional.app.test.utils.assertAppIsClosed
import com.hulkdx.findprofessional.app.test.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.app.test.utils.setProAvailability
import com.hulkdx.findprofessional.app.test.utils.setProUser
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability

fun launchProAvailabilityScreen(
    rule: Rule,
    availability: List<ProfessionalAvailability> = emptyList(),
    email: String = "test@email.com",
    password: String = "somepass",
    block: ProAvailabilityDsl.() -> Unit = {},
): ProAvailabilityDsl {
    setProUser(email, password)
    setProAvailability(availability)

    launchLoginScreen(rule) {
        typeEmail(email)
        typePassword(password)
        pressSignInButton()
    }.verify {
        proScheduleScreenShown()
    }

    rule.onNodeWithText("Availability").performClick()

    return ProAvailabilityDsl(rule).apply(block)
}

class ProAvailabilityDsl(
    private val rule: Rule,
) {
    fun verify(block: ProAvailabilityVerify.() -> Unit) = ProAvailabilityVerify(rule).apply(block)

    fun selectDate(day: String) {
        rule.onNodeWithText(day).performClick()
    }
}

class ProAvailabilityVerify(
    private val rule: Rule,
) {
    fun assertDateHighlighted(day: String) {
        rule.onNodeWithText(day)
            .assertIsDisplayed()
            .assert(hasTestTag("SelectedDay"))
    }

    fun proAvailabilityDetailShown() {
        rule.assertNodeIsDisplayed("ProAvailabilityDetailScreen")
    }
}
