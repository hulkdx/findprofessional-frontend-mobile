package com.hulkdx.findprofessional.common.feature.authentication.login

import com.hulkdx.findprofessional.common.feature.authentication.model.Auth
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import com.hulkdx.findprofessional.common.utils.post
import io.ktor.client.*

interface LoginApi {
    suspend fun login(request: AuthRequest): Auth
}

class LoginApiImpl(
    private val client: HttpClient,
) : LoginApi {
    override suspend fun login(request: AuthRequest): Auth {
        return client.post(urlString, request)
    }

    companion object {
        const val urlString = "auth/login"
    }
}
