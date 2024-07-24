@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.profile

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hulkdx.findprofessional.app.test.screen.home.launchHomeScreen
import com.hulkdx.findprofessional.app.test.utils.Rule
import com.hulkdx.findprofessional.app.test.utils.assertNodeIsDisplayed


fun launchProfileScreen(
    rule: Rule,
    email: String = "test@email.com",
    password: String = "somepass",
    block: ProfileDsl.() -> Unit,
): ProfileDsl {
    launchHomeScreen(
        rule,
        email,
        password
    ) {
        pressProfile()
    }.verify {
        profileScreenShown()
    }
    return ProfileDsl(rule).apply(block)
}

class ProfileDsl(
    private val rule: Rule,
) {
    fun pressBecomeCoachButton() {
        rule.onNodeWithText("Become a coach")
            .performClick()
    }

    fun verify(block: ProfileVerify.() -> Unit) = ProfileVerify(rule).apply(block)
}

class ProfileVerify(
    private val rule: Rule,
) {
    fun signUpProScreenShown() {
        rule.assertNodeIsDisplayed("SignUpProScreen")
    }
}
