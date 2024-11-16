@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.login

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import com.hulkdx.findprofessional.app.test.utils.Rule
import com.hulkdx.findprofessional.app.test.utils.assertNodeIsDisplayed

fun launchLoginScreen(
    rule: Rule,
    block: LoginDsl.() -> Unit,
): LoginDsl {
    rule.assertNodeIsDisplayed("LoginScreen")

    return LoginDsl(rule).apply(block)
}

class LoginDsl(
    private val rule: Rule,
) {
    fun typeEmail(email: String) {
        rule.onNodeWithText("Email")
            .performScrollTo()
            .performTextInput(email)
    }

    fun typePassword(password: String) {
        rule.onNodeWithText("Password")
            .performScrollTo()
            .performTextInput(password)
    }

    fun pressSignUpButton() {
        rule.onNodeWithTag("Register")
            .performScrollTo()
            .performClick()
    }

    fun pressSignInButton() {
        rule.onNodeWithText("Login")
            .performScrollTo()
            .performClick()
    }

    fun verify(block: LoginVerify.() -> Unit) = LoginVerify(rule).apply(block)
}

class LoginVerify(
    private val rule: Rule,
) {
    fun signupScreenShown() {
        rule.assertNodeIsDisplayed("SignUpScreen")
    }

    fun homeScreenShown() {
        rule.assertNodeIsDisplayed("HomeScreen")
    }

    fun proScheduleScreenShown() {
        rule.assertNodeIsDisplayed("ProScheduleScreen")
    }

    fun then(block: LoginDsl.() -> Unit) = LoginDsl(rule).apply(block)
}
