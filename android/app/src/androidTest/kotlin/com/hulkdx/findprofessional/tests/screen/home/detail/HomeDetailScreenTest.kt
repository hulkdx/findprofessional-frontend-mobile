package com.hulkdx.findprofessional.tests.screen.home.detail

import com.hulkdx.findprofessional.utils.ScreenTest
import org.junit.Test

class HomeDetailScreenTest : ScreenTest() {
    @Test
    fun performReviewShowAll() {
        launchHomeDetailScreen(composeRule) {
            pressShowAllReview()
        }.verify {
            reviewScreenShown()
        }
    }

    @Test
    fun performBook() {
        launchHomeDetailScreen(composeRule) {
            pressBookButton()
        }.verify {
            bookScreenShown()
        }
    }
}
