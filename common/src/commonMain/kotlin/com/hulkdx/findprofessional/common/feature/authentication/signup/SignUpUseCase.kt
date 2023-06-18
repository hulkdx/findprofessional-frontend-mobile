@file:Suppress("MoveVariableDeclarationIntoWhen")

package com.hulkdx.findprofessional.common.feature.authentication.signup

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.config.storage.RefreshTokenStorage
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import com.hulkdx.findprofessional.common.utils.generalError
import com.hulkdx.findprofessional.resources.MR
import dev.icerock.moko.resources.desc.desc
import io.ktor.client.plugins.*
import io.ktor.http.*

class SignUpUseCase(
    private val navigator: Navigator,
    private val signUpApi: SignUpApi,
    private val accessTokenStorage: AccessTokenStorage,
    private val refreshTokenStorage: RefreshTokenStorage,
) {

    suspend fun onSubmitClicked(request: AuthRequest) = try {
        val (token, user) = signUpApi.register(request)
        val (accessToken, refreshToken) = token
        accessTokenStorage.set(accessToken)
        refreshTokenStorage.set(refreshToken)
        navigator.navigate(NavigationScreen.Home)
        null
    } catch (e: Throwable) {
        val statusCode = (e as? ClientRequestException)?.response?.status
        when (statusCode) {
            HttpStatusCode.Conflict -> MR.strings.emailExists.desc()
            else -> e.generalError()
        }
    }
}
