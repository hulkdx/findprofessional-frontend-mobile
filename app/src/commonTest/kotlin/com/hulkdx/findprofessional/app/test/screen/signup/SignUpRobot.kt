@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.signup

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hulkdx.findprofessional.app.test.screen.login.launchLoginScreen
import com.hulkdx.findprofessional.app.test.utils.Rule
import com.hulkdx.findprofessional.app.test.utils.assertNodeIsDisplayed

fun launchSignUpScreen(
    rule: Rule,
    block: SignUpDsl.() -> Unit,
): SignUpDsl {
    launchLoginScreen(rule) {
        pressSignUpButton()
    }
    return SignUpDsl(rule).apply(block)
}

class SignUpDsl(
    private val rule: Rule,
) {
    fun pressSignUpButton() {
        rule.onNodeWithText("Sign Up")
            .performClick()
    }

    fun verify(block: SignUpVerify.() -> Unit) = SignUpVerify(rule).apply(block)
}

class SignUpVerify(
    private val rule: Rule,
) {
    fun homeScreenShown() {
        rule.assertNodeIsDisplayed("HomeScreen")
    }

}
