package com.hulkdx.findprofessional.app

import androidx.compose.ui.window.ComposeUIViewController
import com.hulkdx.findprofessional.libs.navigation.decompose.RootComponent
import platform.UIKit.UIViewController

@Suppress("unused", "FunctionName")
fun MainViewController(root: RootComponent): UIViewController {
    return ComposeUIViewController {
        App(root)
    }
}
