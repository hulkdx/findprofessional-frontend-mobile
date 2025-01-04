@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.pro.availability

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hulkdx.findprofessional.app.test.screen.login.launchLoginScreen
import com.hulkdx.findprofessional.app.test.utils.Rule
import com.hulkdx.findprofessional.app.test.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.app.test.utils.setProAvailability
import com.hulkdx.findprofessional.app.test.utils.setProUser
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability

fun launchProAvailabilityScreen(
    rule: Rule,
    email: String = "test@email.com",
    password: String = "somepass",
    availability: List<ProfessionalAvailability> = emptyList(),
    block: ProAvailabilityDsl.() -> Unit,
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

    fun selectFirstDate() {
        rule.onNodeWithText("1").performClick()
    }
}

class ProAvailabilityVerify(
    private val rule: Rule,
) {
    fun proAvailabilityDetailShown() {
        rule.assertNodeIsDisplayed("ProAvailabilityDetailScreen")
    }
}
