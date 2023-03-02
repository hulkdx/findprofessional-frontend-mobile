package com.hulkdx.findprofessional

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.navigation.NavigationComposable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                NavigationComposable()
            }
        }
    }
}
