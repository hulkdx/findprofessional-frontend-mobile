@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.pro.signup

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import com.hulkdx.findprofessional.app.test.screen.profile.launchProfileScreen
import com.hulkdx.findprofessional.app.test.utils.Rule
import com.hulkdx.findprofessional.app.test.utils.assertNodeIsDisplayed

fun launchSignUpProScreen(
    rule: Rule,
    email: String = "test@email.com",
    password: String = "somepass",
    block: SignUpProDsl.() -> Unit,
): SignUpProDsl {
    launchProfileScreen(rule, email, password) {
        pressBecomeCoachButton()
    }.verify {
        signUpProScreenShown()
    }
    return SignUpProDsl(rule).apply(block)
}

class SignUpProDsl(
    private val rule: Rule,
) {

    fun verify(block: SignUpProVerify.() -> Unit) = SignUpProVerify(rule).apply(block)

    fun pressNextButton() {
        rule.onNodeWithText("Next").performClick()
    }

    fun checkWebcamConsent() {
        rule.onNodeWithTag("SignUpProScreen.LazyColumn")
            .assertIsDisplayed()
            .performScrollToNode(hasTestTag("WebcamConsent"))

        rule.onNodeWithTag("WebcamConsent")
            .performClick()
    }

    fun checkIdConsent() {
        rule.onNodeWithTag("SignUpProScreen.LazyColumn")
            .assertIsDisplayed()
            .performScrollToNode(hasTestTag("IdConsent"))

        rule.onNodeWithTag("IdConsent")
            .performClick()
    }

    fun pressRegisterButton() {
        rule.onNodeWithTag("SignUpProScreen.LazyColumn")
            .assertIsDisplayed()
            .performScrollToNode(hasText("Register"))

        rule.onNodeWithText("Register")
            .performClick()
    }
}

class SignUpProVerify(
    private val rule: Rule,
) {
    fun signUpProStepTwoScreenShown() {
        rule.assertNodeIsDisplayed("SignUpProScreen-Step2")
    }

    fun proScheduleScreenShown() {
        rule.assertNodeIsDisplayed("ProScheduleScreen")
    }

    fun then(block: SignUpProDsl.() -> Unit) = SignUpProDsl(rule).apply(block)
}
