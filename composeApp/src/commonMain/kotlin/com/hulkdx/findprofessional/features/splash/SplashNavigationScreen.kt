package com.hulkdx.findprofessional.features.splash

import androidx.compose.material3.Text
import com.hulkdx.findprofessional.core.navigation.Content
import com.hulkdx.findprofessional.core.navigation.NavHostScreen

class SplashNavigationScreen : NavHostScreen() {
    override val content: Content = {
        Text("SplashScreen")
    }
}
