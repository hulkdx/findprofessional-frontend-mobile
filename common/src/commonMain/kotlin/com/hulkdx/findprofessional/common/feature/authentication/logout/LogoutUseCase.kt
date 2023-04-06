package com.hulkdx.findprofessional.common.feature.authentication.logout

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.config.storage.RefreshTokenStorage
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator

interface LogoutUseCase {
    suspend fun logout()
}

class LogoutUseCaseImpl(
    private val navigator: Navigator,
    private val accessTokenStorage: AccessTokenStorage,
    private val refreshTokenStorage: RefreshTokenStorage,
): LogoutUseCase {
    override suspend fun logout() {
        val isAlreadyLoggedOut = accessTokenStorage.get() == null && refreshTokenStorage.get() == null
        if (isAlreadyLoggedOut) {
            return
        }
        accessTokenStorage.remove()
        refreshTokenStorage.remove()
        navigator.navigate(NavigationScreen.Login)
    }
}
