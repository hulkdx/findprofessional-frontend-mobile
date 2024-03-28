package com.hulkdx.findprofessional.tests.screen.signuppro

import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import com.hulkdx.findprofessional.utils.Rule
import com.hulkdx.findprofessional.utils.assertAppIsClosed
import com.hulkdx.findprofessional.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.utils.onNodeWithTextRes
import com.hulkdx.findprofessional.utils.pressBackButton

fun launchSignUpProScreen(
    rule: Rule,
    block: SignUpProDsl.() -> Unit,
): SignUpProDsl {
    return SignUpProDsl(rule).apply(block)
}

class SignUpProDsl(
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

    fun verify(block: SignUpProVerify.() -> Unit) = SignUpProVerify(rule).apply(block)
}

class SignUpProVerify(
    private val rule: Rule,
) {
    fun homeScreenShown() {
        rule.assertNodeIsDisplayed("HomeScreen")
    }

    fun appIsClosed() {
        rule.assertAppIsClosed()
    }

    fun then(block: SignUpProDsl.() -> Unit) = SignUpProDsl(rule).apply(block)

}
