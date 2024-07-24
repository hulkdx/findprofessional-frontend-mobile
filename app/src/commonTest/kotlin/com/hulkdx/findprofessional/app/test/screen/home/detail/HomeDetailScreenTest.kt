@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.home.detail

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.test.runAppUiTest
import kotlin.test.Test

class HomeDetailScreenTest {

    @Test
    fun performReviewShowAll() = runAppUiTest {
        launchHomeDetailScreen(this) {
            pressShowAllReview()
        }.verify {
            reviewScreenShown()
        }
    }

    @Test
    fun performBook() = runAppUiTest {
        launchHomeDetailScreen(this) {
            pressBookButton()
        }.verify {
            bookScreenShown()
        }
    }
}
