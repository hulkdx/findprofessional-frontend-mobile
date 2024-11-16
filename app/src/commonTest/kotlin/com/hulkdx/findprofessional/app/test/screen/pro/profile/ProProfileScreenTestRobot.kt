@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.pro.profile

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hulkdx.findprofessional.app.test.screen.login.launchLoginScreen
import com.hulkdx.findprofessional.app.test.utils.Rule
import com.hulkdx.findprofessional.app.test.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.app.test.utils.setProUser

fun launchProProfileScreen(
    rule: Rule,
    email: String = "test@email.com",
    password: String = "somepass",
    block: ProProfileDsl.() -> Unit,
): ProProfileDsl {
    setProUser(email, password)

    launchLoginScreen(rule) {
        typeEmail(email)
        typePassword(password)
        pressSignInButton()
    }.verify {
        proScheduleScreenShown()
    }

    rule.onNodeWithText("Profile").performClick()

    return ProProfileDsl(rule).apply(block)
}

class ProProfileDsl(
    private val rule: Rule,
) {
    fun verify(block: ProProfileVerify.() -> Unit) = ProProfileVerify(rule).apply(block)

    fun pressEditProfile() {
        rule.onNodeWithText("Edit Profile").performClick()
    }

}

class ProProfileVerify(
    private val rule: Rule,
) {

    fun editProProfileShown() {
        rule.assertNodeIsDisplayed("EditProProfileScreen")
    }
}
