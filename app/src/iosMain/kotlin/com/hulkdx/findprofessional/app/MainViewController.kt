package com.hulkdx.findprofessional.app

import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.ApplicationLifecycle
import com.hulkdx.findprofessional.libs.navigation.decompose.RootComponent
import platform.UIKit.UIViewController

@Suppress("unused", "FunctionName")
fun MainViewController(): UIViewController {
    // If this caused issue, use context like this:
    // https://arkivanov.github.io/Decompose/getting-started/quick-start/#ios-with-compose-or-swiftui-without-the-experimental-applicationlifecycle
    val root = RootComponent(
        navigation = get(),
        componentContext = DefaultComponentContext(ApplicationLifecycle()),
    )
    return ComposeUIViewController {
        App(root)
    }
}
