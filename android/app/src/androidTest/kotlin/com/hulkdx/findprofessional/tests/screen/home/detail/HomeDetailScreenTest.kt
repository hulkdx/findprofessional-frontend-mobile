package com.hulkdx.findprofessional.tests.screen.home.detail

import com.hulkdx.findprofessional.utils.ScreenTest
import org.junit.Test

class HomeDetailScreenTest : ScreenTest() {
    @Test
    fun performHomeDetail() {
        launchHomeDetailScreen(composeRule) {
            pressShowAllReview()
        }.verify {
            reviewScreenShown()
        }
    }
}
