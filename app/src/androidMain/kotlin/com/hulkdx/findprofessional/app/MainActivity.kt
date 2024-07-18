package com.hulkdx.findprofessional.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hulkdx.findprofessional.libs.navigation.decompose.defaultComponentContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val ctx = defaultComponentContext()
        setContent {
            App(ctx)
        }
    }
}
