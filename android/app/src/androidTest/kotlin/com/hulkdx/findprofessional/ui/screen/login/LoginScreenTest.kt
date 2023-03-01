package com.hulkdx.findprofessional.ui.screen.login

import android.graphics.Bitmap
import android.os.Environment
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import com.hulkdx.findprofessional.MainActivity
import com.hulkdx.findprofessional.common.config.api.InMemoryApi
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import com.hulkdx.findprofessional.utils.UiTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File
import java.io.FileOutputStream

class LoginScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val rule = UiTestRule()

    @Before
    fun setUp() {
        InMemoryApi.loadKoinModules()
    }

    @After
    fun tearDown() {
        InMemoryApi.unloadKoinModules()
        val bitmap = composeRule.onRoot().captureToImage().asAndroidBitmap()
        val screenshotsFolder = composeRule.activity.filesDir.absolutePath
        val folder = File(screenshotsFolder)
        if (!folder.exists()) {
            folder.mkdirs()
        }
        val screenshotPath = "$screenshotsFolder/screenshot.png"
        val file = File(screenshotPath)
        if (!file.exists()) {
            file.createNewFile()
        }
        FileOutputStream(screenshotPath).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
    }

//    @Test
//    fun performSignUp() {
//        launchLoginScreen(composeRule) {
//            pressSignUpButton()
//        }.verify {
//            signupScreenShown()
//        }
//    }

    @Test
    fun performLogin() {
        InMemoryApi.user = AuthRequest("test@email.com", "somepass")

        launchLoginScreen(composeRule) {
            typeEmail("test@email.com")
            typePassword("somepass")
            pressSignInButton()
        }.verify {
            mainScreenShown()
        }
    }
}
