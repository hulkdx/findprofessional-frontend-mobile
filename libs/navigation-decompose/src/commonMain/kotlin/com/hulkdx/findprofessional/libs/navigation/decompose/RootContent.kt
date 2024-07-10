package com.hulkdx.findprofessional.libs.navigation.decompose

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.hulkdx.findprofessional.core.navigation.NavigationScreen

@Composable
fun RootContent(
    componentContext: ComponentContext,
    screen: @Composable (NavigationScreen) -> Unit,
) {
    val component = RootComponent(componentContext.impl)
    Children(
        stack = component.stack,
        animation = stackAnimation(slide()),
    ) {
        val child = it.instance
        screen(child)
    }
}
