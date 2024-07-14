@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.hulkdx.findprofessional.app.App
import com.hulkdx.findprofessional.app.di.appModule
import com.hulkdx.findprofessional.libs.navigation.decompose.ComponentContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


fun runAppUiTest(
    effectContext: CoroutineContext = EmptyCoroutineContext,
    block: ComposeUiTest.() -> Unit,
) {
    stopKoin()
    startKoin {
        modules(appModule + testModule)
    }
    runComposeUiTest(effectContext) {
        setAppContent()
        block()
    }
}

fun ComposeUiTest.setAppContent() {
    setContent {
        CompositionLocalProvider(
            LocalViewModelStoreOwner provides object : ViewModelStoreOwner {
                override val viewModelStore: ViewModelStore
                    get() = ViewModelStore()

            }
        ) {
            val lifecycle = LifecycleRegistry()
            App(ComponentContext(DefaultComponentContext(lifecycle = lifecycle)))
            lifecycle.resume()
        }
    }
}
