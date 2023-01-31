package com.hulkdx.findprofessional.common.feature.authentication.signup

import com.hulkdx.findprofessional.common.feature.authentication.signup.exception.EmailExistsException
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import io.ktor.client.plugins.*
import io.ktor.http.*


class SignUpUseCase(
    private val signUpApi: SignUpApi,
) {
    suspend fun register(request: RegisterRequest) = mapException {
        signUpApi.register(request)
    }

    private inline fun mapException(block: () -> Unit) {
        try {
            block()
        } catch (e: ClientRequestException) {
            if (e.response.status == HttpStatusCode.Conflict) {
                throw EmailExistsException()
            }
            throw e
        }
    }
}
