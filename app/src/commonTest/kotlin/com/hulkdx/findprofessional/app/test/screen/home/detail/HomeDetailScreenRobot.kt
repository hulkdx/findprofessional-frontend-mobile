@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.home.detail

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hulkdx.findprofessional.app.test.screen.home.launchHomeScreen
import com.hulkdx.findprofessional.app.test.utils.Rule
import com.hulkdx.findprofessional.app.test.utils.assertNodeIsDisplayed
import com.hulkdx.findprofessional.app.test.utils.lazyColumnScrollTo
import com.hulkdx.findprofessional.app.test.utils.setUser


fun launchHomeDetailScreen(
    rule: Rule,
    email: String = "test@email.com",
    password: String = "somepass",
    professionalId: Int = 1,
    block: HomeDetailScreenDsl.() -> Unit,
): HomeDetailScreenDsl {
    setUser(email, password)

    launchHomeScreen(rule) {
        pressProfessional(professionalId)
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

    fun pressBookButton() {
        rule.onNodeWithText("Book now")
            .performClick()
    }

    fun verify(block: HomeDetailScreenVerify.() -> Unit) = HomeDetailScreenVerify(rule).apply(block)
}

class HomeDetailScreenVerify(
    private val rule: Rule,
) {
    fun reviewScreenShown() {
        rule.assertNodeIsDisplayed("ReviewScreen")
    }

    fun bookScreenShown() {
        rule.assertNodeIsDisplayed("BookingTimeScreen")
    }
}
