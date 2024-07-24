@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.home

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.test.runAppUiTest
import kotlin.test.Test

class HomeScreenTest {

    @Test
    fun performHomeDetail() = runAppUiTest {
        launchHomeScreen(this) {
            pressProfessional()
        }.verify {
            homeDetailScreenShown()
        }
    }

    @Test
    fun navigateToProfile() = runAppUiTest {
        launchHomeScreen(this) {
            pressProfile()
        }.verify {
            profileScreenShown()
        }
    }
}
