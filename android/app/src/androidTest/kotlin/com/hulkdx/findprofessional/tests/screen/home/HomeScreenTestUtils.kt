package com.hulkdx.findprofessional.tests.screen.home

import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hulkdx.findprofessional.InMemoryApi
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.tests.screen.login.launchLoginScreen
import com.hulkdx.findprofessional.utils.Rule
import com.hulkdx.findprofessional.utils.assertNodeIsDisplayed


fun launchHomeScreen(
    rule: Rule,
    email: String = "test@email.com",
    password: String = "somepass",
    block: HomeScreenDsl.() -> Unit,
): HomeScreenDsl {
    InMemoryApi.setUser(email, password)

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
    fun pressAnyProfessional() {
        val professional = InMemoryApi.professionals[0]
        pressProfessional(professional)
    }

    fun pressProfessional(professional: Professional) {
        rule.onNodeWithTag("pro-${professional.id}")
            .performClick()
    }

    fun pressProfile() {
        rule.onNodeWithText("Your Profile")
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
