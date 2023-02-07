package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.hulkdx.findprofessional.core.navigation.Content
import com.hulkdx.findprofessional.core.navigation.SlideNavigationScreen

class SignUpNavigationScreen : SlideNavigationScreen() {
    override val content: Content = { SignUpScreen() }
}

// TODO: create a new feature / move this
class MainNavigationScreen: SlideNavigationScreen() {
    override val content: Content = { Text(modifier = Modifier.testTag("MainScreen"), text = "MainScreen") }
}
