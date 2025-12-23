@file:Suppress("MoveVariableDeclarationIntoWhen")

package com.hulkdx.findprofessional.feature.authentication.signup

import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.emailExists
import com.hulkdx.findprofessional.feature.authentication.storage.UserStorage
import com.hulkdx.findprofessional.core.utils.generalError
import com.hulkdx.findprofessional.core.utils.toStringOrRes
import com.hulkdx.findprofessional.feature.authentication.signup.model.RegisterRequest
import io.ktor.client.plugins.ClientRequestException
import io.ktor.http.HttpStatusCode

class SignUpUseCase(
    private val signUpApi: SignUpApi,
    private val userStorage: UserStorage,
) {
    suspend fun signUp(request: RegisterRequest) = try {
        val userData = signUpApi.register(request)
        userStorage.set(userData)
        null
    } catch (e: Throwable) {
        val statusCode = (e as? ClientRequestException)?.response?.status
        when (statusCode) {
            HttpStatusCode.Conflict -> Res.string.emailExists.toStringOrRes()
            else -> e.generalError()
        }
    }
}
