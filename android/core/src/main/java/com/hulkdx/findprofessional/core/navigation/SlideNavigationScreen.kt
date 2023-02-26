@file:OptIn(ExperimentalAnimationApi::class)

package com.hulkdx.findprofessional.core.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween

abstract class SlideNavigationScreen : BasicNavigationScreen() {
    override val enterTransition: EnterTransitionType = {
        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, tween(300))
    }
    override val popExitTransition: ExitTransitionType = {
        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(300))
    }
}
