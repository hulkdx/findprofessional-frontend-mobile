package com.hulkdx.findprofessional.tests.screen.profile

import com.hulkdx.findprofessional.utils.ScreenTest
import org.junit.Test


class ProfileScreenTest : ScreenTest() {
    @Test
    fun performSignupProScreen() {
        launchProfileScreen(composeRule) {
            pressBecomeCoachButton()
        }.verify {
            signUpProScreenShown()
        }
    }
}
