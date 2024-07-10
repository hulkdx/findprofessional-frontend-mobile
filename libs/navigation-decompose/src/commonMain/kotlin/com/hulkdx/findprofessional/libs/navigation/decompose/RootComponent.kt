package com.hulkdx.findprofessional.libs.navigation.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.hulkdx.findprofessional.core.navigation.NavigationScreen

// TODO: remove componentContext
internal class RootComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext {
    val navigation = StackNavigation<NavigationScreen>()

    val stack: Value<ChildStack<*, NavigationScreen>> =
        childStack(
            source = navigation,
            serializer = NavigationScreen.serializer(),
            initialConfiguration = NavigationScreen.Splash,
            handleBackButton = true,
            childFactory = { screen, _ -> screen },
        )
}
