package com.hulkdx.findprofessional.tests.screen.profile

import androidx.compose.ui.test.performClick
import com.hulkdx.findprofessional.tests.screen.home.launchHomeScreen
import com.hulkdx.findprofessional.utils.Rule
import com.hulkdx.findprofessional.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.utils.onNodeWithTextRes


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
        rule.onNodeWithTextRes(MR.strings.becomeCoach.resourceId)
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
