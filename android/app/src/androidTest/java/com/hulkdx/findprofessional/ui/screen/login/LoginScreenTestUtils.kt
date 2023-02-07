package com.hulkdx.findprofessional.ui.screen.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.hulkdx.findprofessional.resources.MR
import com.hulkdx.findprofessional.utils.Rule
import com.hulkdx.findprofessional.utils.onNodeWithTagRes
import com.hulkdx.findprofessional.utils.onNodeWithTextRes

fun launchLoginScreen(
    rule: Rule,
    block: LoginDsl.() -> Unit,
) = LoginDsl(rule).apply(block)

class LoginDsl(
    private val rule: Rule
) {
    fun typeEmail(email: String) {
        rule.onNodeWithTextRes(MR.strings.email.resourceId)
            .performTextInput(email)
    }

    fun typePassword(password: String) {
        rule.onNodeWithTextRes(MR.strings.password.resourceId)
            .performTextInput(password)
    }

    fun pressSignUpButton() {
        rule.onNodeWithTagRes(MR.strings.signUp.resourceId)
            .performClick()
    }

    fun pressSignInButton() {
        rule.onNodeWithTextRes(MR.strings.signIn.resourceId)
            .performClick()
    }

    fun verify(block: LoginVerify.() -> Unit) = LoginVerify(rule).apply(block)
}

class LoginVerify(
    private val rule: Rule
) {
    fun signupScreenIsShown() {
        rule.onNodeWithTag("SignUpScreen")
            .assertIsDisplayed()
    }
}
