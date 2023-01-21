package com.hulkdx.findprofessional.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator


class NavigatorImpl: Navigator {

    val screenState = mutableStateOf<NavigationScreen?>(null, neverEqualPolicy())

    override fun navigate(screen: NavigationScreen) {
        screenState.value = screen
    }
}
