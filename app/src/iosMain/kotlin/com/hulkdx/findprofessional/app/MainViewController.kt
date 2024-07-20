@file:OptIn(ExperimentalDecomposeApi::class)

package com.hulkdx.findprofessional.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureIcon
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureOverlay
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.hulkdx.findprofessional.libs.navigation.decompose.RootComponent
import platform.UIKit.UIViewController

@Suppress("unused", "FunctionName")
fun MainViewController(root: RootComponent): UIViewController {
    return ComposeUIViewController {
        PredictiveBackGestureOverlay(
            backDispatcher = root.backHandler as BackDispatcher,
            backIcon = { progress, _ ->
                PredictiveBackGestureIcon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    progress = progress,
                )
            },
        ) {
            App(root)
        }
    }
}
