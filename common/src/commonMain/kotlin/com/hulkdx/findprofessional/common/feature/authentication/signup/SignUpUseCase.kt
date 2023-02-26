@file:Suppress("MoveVariableDeclarationIntoWhen")

package com.hulkdx.findprofessional.common.feature.authentication.signup

import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import com.hulkdx.findprofessional.common.utils.generalError
import com.hulkdx.findprofessional.resources.MR
import dev.icerock.moko.resources.desc.desc
import io.ktor.client.plugins.*
import io.ktor.http.*

class SignUpUseCase(
    private val signUpApi: SignUpApi,
    private val navigator: Navigator,
) {

    suspend fun register(request: AuthRequest) = try {
        signUpApi.register(request)
        navigator.navigate(NavigationScreen.Main)
        null
    } catch (e: Throwable) {
        val statusCode = (e as? ClientRequestException)?.response?.status
        when (statusCode) {
            HttpStatusCode.Conflict -> MR.strings.emailExists.desc()
            else -> e.generalError()
        }
    }
}
