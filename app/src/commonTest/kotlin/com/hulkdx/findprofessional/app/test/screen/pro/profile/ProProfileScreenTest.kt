/*
@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.pro.profile

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.test.runAppUiTest
import kotlin.test.Test

class ProProfileScreenTest {
    @Test
    fun performEditProfile() = runAppUiTest {
        launchProProfileScreen(this) {
            pressEditProfile()
        }.verify {
            editProProfileShown()
        }
    }
}
*/
