@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.resume
import com.hulkdx.findprofessional.app.App
import com.hulkdx.findprofessional.app.di.appModule
import com.hulkdx.findprofessional.app.test.utils.deleteDataStoreFile
import com.hulkdx.findprofessional.app.test.utils.get
import com.hulkdx.findprofessional.app.test.utils.inMemoryApi
import com.hulkdx.findprofessional.app.test.utils.isIOS
import com.hulkdx.findprofessional.libs.navigation.decompose.RootComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


fun runAppUiTest(
    effectContext: CoroutineContext = EmptyCoroutineContext,
    before: () -> Unit = {},
    after: () -> Unit = {},
    test: ComposeUiTest.() -> Unit,
) {
    val lifecycle = LifecycleRegistry()
    try {
        if (isIOS()) {
            runCatching { startKoin { modules(appModule) } }
        }
        loadKoinModules(testModule)
        deleteDataStoreFile()
        inMemoryApi.loadKoinModules()
        lifecycle.resume()

        before()
        runComposeUiTest(effectContext) {
            setAppContent(lifecycle)
            test()
        }
        after()
    } finally {
        lifecycle.destroy()
        inMemoryApi.unloadKoinModules()
    }
}

fun ComposeUiTest.setAppContent(lifecycle: LifecycleRegistry) {
    val root = RootComponent(get(), DefaultComponentContext(lifecycle = lifecycle))
    setContent {
        provideViewModelStoreForIOS {
            App(root)
        }
    }
}

@Composable
private fun provideViewModelStoreForIOS(content: @Composable () -> Unit) {
    if (isIOS()) {
        val a = object : ViewModelStoreOwner {
            override val viewModelStore = ViewModelStore()
        }
        CompositionLocalProvider(LocalViewModelStoreOwner provides a, content)
    } else {
        content()
    }
}
