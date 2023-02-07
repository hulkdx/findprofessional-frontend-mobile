package com.hulkdx.findprofessional.common.feature.authentication.signup

import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import com.hulkdx.findprofessional.resources.MR
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import io.ktor.client.plugins.*
import io.ktor.http.*


class SignUpUseCase(
    private val signUpApi: SignUpApi,
    private val navigator: Navigator,
) {
    suspend fun register(request: RegisterRequest) = mapError {
        signUpApi.register(request)
        navigator.navigate(NavigationScreen.Main)
    }

    private inline fun mapError(block: () -> Unit): StringDesc? {
        return try {
            block()
            null
        } catch (e: Throwable) {
            val error = if (e is ClientRequestException &&
                e.response.status == HttpStatusCode.Conflict) {
                MR.strings.emailExists
            } else {
                MR.strings.generalError
            }
            return error.desc()
        }
    }
}
