package com.hulkdx.findprofessional.ui.screen.signup

import androidx.compose.ui.test.performClick
import com.hulkdx.findprofessional.resources.MR
import com.hulkdx.findprofessional.ui.screen.login.launchLoginScreen
import com.hulkdx.findprofessional.utils.Rule
import com.hulkdx.findprofessional.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.utils.onNodeWithTextRes

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
        rule.onNodeWithTextRes(MR.strings.signUp.resourceId)
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
