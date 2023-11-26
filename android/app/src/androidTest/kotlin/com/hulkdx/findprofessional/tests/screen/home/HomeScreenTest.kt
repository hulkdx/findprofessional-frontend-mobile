package com.hulkdx.findprofessional.tests.screen.home

import com.hulkdx.findprofessional.utils.ScreenTest
import org.junit.Test

class HomeScreenTest : ScreenTest() {
    @Test
    fun performHomeDetail() {
        launchHomeScreen(composeRule) {
            pressAnyProfessional()
        }.verify {
            homeDetailScreenShown()
        }
    }
}
