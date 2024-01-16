package com.hulkdx.findprofessional.tests.screen.home.detail

import androidx.compose.ui.test.performClick
import com.hulkdx.findprofessional.InMemoryApi
import com.hulkdx.findprofessional.tests.screen.home.launchHomeScreen
import com.hulkdx.findprofessional.utils.Rule
import com.hulkdx.findprofessional.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.utils.lazyColumnScrollTo


fun launchHomeDetailScreen(
    rule: Rule,
    email: String = "test@email.com",
    password: String = "somepass",
    block: HomeDetailScreenDsl.() -> Unit,
): HomeDetailScreenDsl {
    InMemoryApi.setUser(email, password)

    launchHomeScreen(rule) {
        pressAnyProfessional()
    }.verify {
        homeDetailScreenShown()
    }

    return HomeDetailScreenDsl(rule).apply(block)
}


class HomeDetailScreenDsl(
    private val rule: Rule,
) {
    fun pressShowAllReview() {
        rule.lazyColumnScrollTo(
            lazyColumnTestTag = "HomeDetailScreen.LazyColumn",
            nodeTestTag = "showAllReviews"
        ).performClick()
    }

    fun verify(block: HomeDetailScreenVerify.() -> Unit) = HomeDetailScreenVerify(rule).apply(block)
}

class HomeDetailScreenVerify(
    private val rule: Rule,
) {
    fun reviewScreenShown() {
        rule.assertNodeIsDisplayed("ReviewScreen")
    }
}
