package com.hulkdx.findprofessional.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Left
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Right
import androidx.compose.animation.core.tween

abstract class SlideNavigationScreen : BasicNavigationScreen() {
    override val enterTransition: EnterTransitionType = {
        slideIntoContainer(Left, tween(300))
    }
    override val popExitTransition: ExitTransitionType = {
        slideOutOfContainer(Right, tween(300))
    }
}
