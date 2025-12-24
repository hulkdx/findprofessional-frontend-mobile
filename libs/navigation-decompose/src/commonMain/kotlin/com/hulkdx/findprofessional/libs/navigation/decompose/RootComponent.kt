package com.hulkdx.findprofessional.libs.navigation.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.animation.StackAnimation
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.essenty.backhandler.BackHandler
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.feature.authentication.AuthNavigationScreen
import kotlinx.serialization.KSerializer

class RootComponent(
    private val navigation: StackNavigation<NavigationScreen>,
    componentContext: ComponentContext,
    serializer: KSerializer<NavigationScreen>,
) : ComponentContext by componentContext {
    val stack = childStack(
        source = navigation,
        serializer = serializer,
        initialConfiguration = AuthNavigationScreen.Splash,
        handleBackButton = true,
        childFactory = { screen, ctx -> screen to ctx.viewModelStoreOwner() },
    )

    fun onBackClicked() {
        navigation.pop()
    }
}

expect fun <C : Any, T : Any> backAnimation(
    backHandler: BackHandler,
    onBack: () -> Unit,
): StackAnimation<C, T>
