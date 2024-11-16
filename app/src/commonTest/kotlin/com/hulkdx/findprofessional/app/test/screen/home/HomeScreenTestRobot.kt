@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.home

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hulkdx.findprofessional.app.test.screen.login.launchLoginScreen
import com.hulkdx.findprofessional.app.test.utils.Rule
import com.hulkdx.findprofessional.app.test.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.app.test.utils.setUser


fun launchHomeScreen(
    rule: Rule,
    email: String = "test@email.com",
    password: String = "somepass",
    block: HomeScreenDsl.() -> Unit,
): HomeScreenDsl {
    setUser(email, password)

    launchLoginScreen(rule) {
        typeEmail(email)
        typePassword(password)
        pressSignInButton()
    }.verify {
        homeScreenShown()
    }

    return HomeScreenDsl(rule).apply(block)
}


class HomeScreenDsl(
    private val rule: Rule,
) {
    fun pressProfessional(professionalId: Int = 1) {
        rule.onNodeWithTag("pro-${professionalId}")
            .performClick()
    }

    fun pressProfile() {
        rule.onNodeWithText("Profile")
            .performClick()
    }

    fun verify(block: HomeScreenVerify.() -> Unit) = HomeScreenVerify(rule).apply(block)
}

class HomeScreenVerify(
    private val rule: Rule,
) {
    fun homeDetailScreenShown() {
        rule.assertNodeIsDisplayed("HomeDetailScreen")
    }

    fun profileScreenShown() {
        rule.assertNodeIsDisplayed("ProfileScreen")
    }
}
