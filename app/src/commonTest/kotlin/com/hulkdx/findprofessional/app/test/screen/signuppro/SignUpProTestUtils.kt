@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.signuppro

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
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

    fun pressRegisterButton() {
        rule.onNodeWithText("Register").performClick()
    }
}

class SignUpProVerify(
    private val rule: Rule,
) {
    fun signUpProStepTwoScreenShown() {
        rule.assertNodeIsDisplayed("SignUpProScreen-Step2")
    }
}
