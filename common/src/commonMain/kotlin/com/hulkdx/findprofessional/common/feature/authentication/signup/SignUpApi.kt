package com.hulkdx.findprofessional.common.feature.authentication.signup

import com.hulkdx.findprofessional.common.feature.authentication.model.Auth
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import com.hulkdx.findprofessional.common.utils.post
import io.ktor.client.*

interface SignUpApi {
    suspend fun register(request: AuthRequest): Auth
}

class SignUpApiImpl(
    private val client: HttpClient,
) : SignUpApi {

    override suspend fun register(request: AuthRequest): Auth {
        return client.post(urlString, request)
    }

    companion object {
        const val urlString = "auth/register"
    }
}
