package com.hulkdx.findprofessional.feature.developer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.hulkdx.findprofessional.core.platform.isDebug
import com.hulkdx.findprofessional.feature.developer.storage.DeveloperStorage
import org.koin.compose.koinInject

object DeveloperAuthPrefill {
    const val EMAIL = "test@gmail.com"
    const val PASSWORD = "123456@a"
    const val FIRSTNAME = "John"
    const val LASTNAME = "C."
    const val COACH_TYPE = "Life coach"
    const val ABOUT_ME =
        "Helps you build confidence, clarity, and healthier habits. Great for navigating big life changes and staying accountable."
    const val SESSION_LINK = "https://meet.google.com/abc-defg-hij"
    const val SESSION_PLATFORMS = "Google Meet"
    const val PRICE = "20"
    const val CURRENCY = "EUR"
    const val WEBCAM_CONSENT = true
    const val ID_CONSENT = true
}

@Composable
inline fun ShowPrefillOnDebug(crossinline showPrefill: suspend DeveloperAuthPrefill.() -> Unit) {
    if (isDebug()) {
        val developerStorage = koinInject<DeveloperStorage>()
        LaunchedEffect(developerStorage) {
            val isAuthPrefill = developerStorage.getAsBoolean(DeveloperStorage.Key.AuthPrefill)
            if (isAuthPrefill) {
                showPrefill(DeveloperAuthPrefill)
            }
        }
    }
}
