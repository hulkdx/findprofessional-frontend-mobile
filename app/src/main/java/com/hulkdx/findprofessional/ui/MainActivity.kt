package com.hulkdx.findprofessional.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.activity.compose.setContent
import com.hulkdx.findprofessional.ui.screen.signup.SignUpScreen
import com.hulkdx.findprofessional.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    SignUpScreen()
                }
            }
        }
    }
}
