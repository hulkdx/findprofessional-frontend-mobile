package com.hulkdx.findprofessional.tests.screen.signup

import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import com.hulkdx.findprofessional.tests.screen.login.launchLoginScreen
import com.hulkdx.findprofessional.utils.Rule
import com.hulkdx.findprofessional.utils.assertAppIsClosed
import com.hulkdx.findprofessional.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.utils.onNodeWithTextRes
import com.hulkdx.findprofessional.utils.pressBackButton

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

    fun pressBackButton() {
        Espresso.closeSoftKeyboard()
        rule.pressBackButton()
    }

    fun verify(block: SignUpVerify.() -> Unit) = SignUpVerify(rule).apply(block)
}

class SignUpVerify(
    private val rule: Rule,
) {
    fun homeScreenShown() {
        rule.assertNodeIsDisplayed("HomeScreen")
    }

    fun appIsClosed() {
        rule.assertAppIsClosed()
    }

    fun then(block: SignUpDsl.() -> Unit) = SignUpDsl(rule).apply(block)

}
