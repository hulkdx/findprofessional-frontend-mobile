package com.hulkdx.findprofessional

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.navigation.NavigationComposable
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        window.navigationBarColor = Color.BLACK

        setContent {
            AppTheme {
                NavigationComposable()
            }
        }
    }
}
