package com.hulkdx.findprofessional.ui.screen.signup

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.hulkdx.findprofessional.MainActivity
import com.hulkdx.findprofessional.R
import com.hulkdx.findprofessional.utils.onNodeWithTagRes
import com.hulkdx.findprofessional.utils.onNodeWithTextRes


fun launchLoginScreen(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    block: SignUpDsl.() -> Unit,
) = SignUpDsl(rule).apply(block)

class SignUpDsl(
    private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
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

    fun verify(block: SignUpVerify.() -> Unit) = SignUpVerify(rule).apply(block)
}

class SignUpVerify(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {

}
