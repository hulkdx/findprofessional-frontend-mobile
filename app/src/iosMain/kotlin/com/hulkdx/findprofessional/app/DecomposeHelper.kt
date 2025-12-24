package com.hulkdx.findprofessional.app

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.pause
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import com.hulkdx.findprofessional.app.navigation.navigationScreenSerializer
import com.hulkdx.findprofessional.libs.navigation.decompose.RootComponent


@Suppress("unused") // used in swift
fun getRoot(): RootComponent {
    val root = RootComponent(
        navigation = get(),
        componentContext = DefaultComponentContext(
            lifecycle = LifecycleRegistry(),
            backHandler = BackDispatcher(),
        ),
        serializer = navigationScreenSerializer,
    )

    return root
}

@Suppress("unused") // used in swift
fun RootComponent.destroy() = (lifecycle as LifecycleRegistry).destroy()

@Suppress("unused") // used in swift
fun RootComponent.resume() = (lifecycle as LifecycleRegistry).resume()

@Suppress("unused") // used in swift
fun RootComponent.stop() = (lifecycle as LifecycleRegistry).stop()

@Suppress("unused") // used in swift
fun RootComponent.pause() = (lifecycle as LifecycleRegistry).pause()
