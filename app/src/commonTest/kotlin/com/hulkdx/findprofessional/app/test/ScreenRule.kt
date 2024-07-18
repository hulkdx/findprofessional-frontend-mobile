@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.resume
import com.hulkdx.findprofessional.app.App
import com.hulkdx.findprofessional.app.test.utils.inMemoryApi
import com.hulkdx.findprofessional.libs.navigation.decompose.ComponentContext
import org.koin.core.context.loadKoinModules
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


fun runAppUiTest(
    effectContext: CoroutineContext = EmptyCoroutineContext,
    block: ComposeUiTest.() -> Unit,
) {
    val lifecycle = LifecycleRegistry()
    try {
        loadKoinModules(testModule)
        inMemoryApi.loadKoinModules()
        lifecycle.resume()
        runComposeUiTest(effectContext) {
            setAppContent(lifecycle)
            block()
        }
    } finally {
        lifecycle.destroy()
        inMemoryApi.unloadKoinModules()
    }
}

fun ComposeUiTest.setAppContent(lifecycle: LifecycleRegistry) {
    setContent {
        App(ComponentContext(DefaultComponentContext(lifecycle = lifecycle)))
    }
}
