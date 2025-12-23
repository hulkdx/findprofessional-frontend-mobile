package com.hulkdx.findprofessional.feature.authentication.login.usecase

import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.feature.authentication.storage.UserStorage
import com.hulkdx.findprofessional.feature.home.HomeNavigationScreen

class LogoutUseCaseImpl(
    private val navigator: Navigator,
    private val userStorage: UserStorage,
) : LogoutUseCase {
    override suspend fun logout() {
        val userData = userStorage.get()
        val accessToken = userData?.token?.accessToken
        val refreshToken = userData?.token?.refreshToken
        val isAlreadyLoggedOut = accessToken == null && refreshToken == null
        if (isAlreadyLoggedOut) {
            userStorage.remove()
            return
        }
        userStorage.remove()
        navigator.navigate(NavigationScreen.Login, HomeNavigationScreen.Home(), inclusive = true)
    }
}
