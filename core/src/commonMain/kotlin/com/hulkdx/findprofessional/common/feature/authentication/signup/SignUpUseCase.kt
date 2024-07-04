@file:Suppress("MoveVariableDeclarationIntoWhen")

package com.hulkdx.findprofessional.common.feature.authentication.signup

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.config.storage.RefreshTokenStorage
import com.hulkdx.findprofessional.common.config.storage.UserStorage
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import com.hulkdx.findprofessional.common.utils.generalError
import com.hulkdx.findprofessional.common.utils.toStringOrRes
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.emailExists
import io.ktor.client.plugins.ClientRequestException
import io.ktor.http.HttpStatusCode

class SignUpUseCase(
    private val navigator: Navigator,
    private val signUpApi: SignUpApi,
    private val accessTokenStorage: AccessTokenStorage,
    private val refreshTokenStorage: RefreshTokenStorage,
    private val userStorage: UserStorage,
) {

    suspend fun onSubmitClicked(request: RegisterRequest) = try {
        val (token, user) = signUpApi.register(request)
        val (accessToken, refreshToken) = token
        accessTokenStorage.set(accessToken)
        refreshTokenStorage.set(refreshToken)
        userStorage.set(user)
        navigator.navigate(NavigationScreen.Home, popTo = NavigationScreen.Login, inclusive = true)
        null
    } catch (e: Throwable) {
        val statusCode = (e as? ClientRequestException)?.response?.status
        when (statusCode) {
            HttpStatusCode.Conflict -> Res.string.emailExists.toStringOrRes()
            else -> e.generalError()
        }
    }
}
