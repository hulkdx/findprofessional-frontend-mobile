package com.hulkdx.findprofessional.ui.screen.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.hulkdx.findprofessional.R
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
        rule.onNodeWithTextRes(R.string.email)
            .performTextInput(email)
    }

    fun typePassword(password: String) {
        rule.onNodeWithTextRes(R.string.password)
            .performTextInput(password)
    }

    fun pressSignUpButton() {
        rule.onNodeWithTagRes(R.string.signUp)
            .performClick()
    }

    fun pressSignInButton() {
        rule.onNodeWithTextRes(R.string.signIn)
            .performClick()
    }

    fun verify(block: LoginVerify.() -> Unit) = LoginVerify(rule).apply(block)
}

class LoginVerify(
    private val rule: Rule
) {
    fun signupScreenIsShown() {
        rule.onNodeWithTextRes(R.string.signUp)
            .assertIsDisplayed()
    }
}
