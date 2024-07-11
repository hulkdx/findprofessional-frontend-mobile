@file:Suppress("MoveVariableDeclarationIntoWhen")

package com.hulkdx.findprofessional.feature.authentication.signup

import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.feature.authentication.login.storage.UserStorage

class SignUpUseCase(
    private val navigator: Navigator,
    private val signUpApi: SignUpApi,
    private val userStorage: UserStorage,
) {
    /*
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
    */
}
