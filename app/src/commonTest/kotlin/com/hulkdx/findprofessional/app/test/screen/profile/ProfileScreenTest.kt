@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.profile

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.test.runAppUiTest
import kotlin.test.Test


class ProfileScreenTest {
    @Test
    fun performSignupProScreen() = runAppUiTest {
        launchProfileScreen(this) {
            pressBecomeCoachButton()
        }.verify {
            signUpProScreenShown()
        }
    }
}
