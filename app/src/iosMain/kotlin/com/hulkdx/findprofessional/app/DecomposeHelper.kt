package com.hulkdx.findprofessional.app

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.pause
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import com.hulkdx.findprofessional.libs.navigation.decompose.RootComponent


fun getRoot(): RootComponent {
    val root = RootComponent(
        navigation = get(),
        componentContext = DefaultComponentContext(
            lifecycle = LifecycleRegistry(),
            backHandler = BackDispatcher(),
        ),
    )

    return root
}

fun RootComponent.destroy() = (lifecycle as LifecycleRegistry).destroy()
fun RootComponent.resume() = (lifecycle as LifecycleRegistry).resume()
fun RootComponent.stop() = (lifecycle as LifecycleRegistry).stop()
fun RootComponent.pause() = (lifecycle as LifecycleRegistry).pause()
