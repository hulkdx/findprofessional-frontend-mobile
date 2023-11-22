package com.hulkdx.findprofessional.common.feature.authentication.login

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.config.storage.RefreshTokenStorage
import com.hulkdx.findprofessional.common.config.storage.UserStorage
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.LoginRequest
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import com.hulkdx.findprofessional.common.utils.generalError

class LoginUseCase(
    private val navigator: Navigator,
    private val api: LoginApi,
    private val accessTokenStorage: AccessTokenStorage,
    private val refreshTokenStorage: RefreshTokenStorage,
    private val userStorage: UserStorage,
) {
    fun onSignUpClicked() {
        navigator.navigate(NavigationScreen.SignUp)
    }

    suspend fun onSignInClicked(request: LoginRequest) = try {
        val (token, user) = api.login(request)
        accessTokenStorage.set(token.accessToken)
        refreshTokenStorage.set(token.refreshToken)
        userStorage.set(user)
        navigator.navigate(NavigationScreen.Home)
        null
    } catch (e: Throwable) {
        e.generalError()
    }

    fun onDevClicked() {
        navigator.navigate(NavigationScreen.Developer)
    }

    suspend fun onSplashScreenLoaded() {
        val isUserLoggedIn = accessTokenStorage.get() != null
        val dest = if (isUserLoggedIn) {
            NavigationScreen.Home
        } else {
            NavigationScreen.Login
        }
        navigator.navigate(screen = dest, popTo = NavigationScreen.Splash, inclusive = true)
    }
}
