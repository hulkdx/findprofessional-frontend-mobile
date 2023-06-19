package com.hulkdx.findprofessional.common.feature.authentication.login

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.config.storage.RefreshTokenStorage
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import com.hulkdx.findprofessional.common.utils.generalError

class LoginUseCase(
    private val navigator: Navigator,
    private val api: LoginApi,
    private val accessTokenStorage: AccessTokenStorage,
    private val refreshTokenStorage: RefreshTokenStorage,
) {
    fun onSignUpClicked() {
        navigator.navigate(NavigationScreen.SignUp)
    }

    suspend fun onSignInClicked(request: AuthRequest) = try {
        val (token, user) = api.login(request)
        val (accessToken, refreshToken) = token
        accessTokenStorage.set(accessToken)
        refreshTokenStorage.set(refreshToken)
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
        if (isUserLoggedIn) {
            navigator.navigate(NavigationScreen.Home)
        } else {
            navigator.navigate(NavigationScreen.Login)
        }
    }
}
