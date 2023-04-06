package com.hulkdx.findprofessional.common.feature.authentication.logout

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.config.storage.RefreshTokenStorage
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
    }
}
