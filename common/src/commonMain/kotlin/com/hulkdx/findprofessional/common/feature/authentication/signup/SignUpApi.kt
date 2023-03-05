package com.hulkdx.findprofessional.common.feature.authentication.signup

import com.hulkdx.findprofessional.common.feature.authentication.login.AuthToken
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import com.hulkdx.findprofessional.common.utils.post
import io.ktor.client.*

interface SignUpApi {
    suspend fun register(request: AuthRequest): AuthToken
}

class SignUpApiImpl(
    private val client: HttpClient,
) : SignUpApi {

    override suspend fun register(request: AuthRequest): AuthToken {
        return client.post("auth/register", request)
    }
}
