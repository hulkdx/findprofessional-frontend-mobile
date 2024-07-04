@file:Suppress("MoveVariableDeclarationIntoWhen")

package com.hulkdx.findprofessional.common.feature.authentication.pro.signup

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.config.storage.RefreshTokenStorage
import com.hulkdx.findprofessional.common.config.storage.UserStorage
import com.hulkdx.findprofessional.common.feature.authentication.pro.signup.model.ProRegisterRequest
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpApi
import com.hulkdx.findprofessional.common.navigation.Navigator
import com.hulkdx.findprofessional.common.utils.StringOrRes

class SignUpProUseCase(
    private val navigator: Navigator,
    private val signUpApi: SignUpApi,
    private val accessTokenStorage: AccessTokenStorage,
    private val refreshTokenStorage: RefreshTokenStorage,
    private val userStorage: UserStorage,
) {

    suspend fun onSubmitClicked(request: ProRegisterRequest): StringOrRes? = TODO()
//    = try {
//        val (token, user) = signUpApi.registerPro(request)
//        val (accessToken, refreshToken) = token
//        accessTokenStorage.set(accessToken)
//        refreshTokenStorage.set(refreshToken)
//        userStorage.set(user)
//        // TODO:
//        navigator.navigate(NavigationScreen.Home, popTo = NavigationScreen.Login, inclusive = true)
//        null
//    } catch (e: Throwable) {
//        val statusCode = (e as? ClientRequestException)?.response?.status
//        when (statusCode) {
//            HttpStatusCode.Conflict -> MR.strings.emailExists.desc()
//            else -> e.generalError()
//        }
//    }
}
