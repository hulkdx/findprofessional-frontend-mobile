package com.hulkdx.findprofessional.common.feature.authentication.signup

import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.common.utils.post
import io.ktor.client.*


class SignUpApi(
    private val client: HttpClient,
) {
    suspend fun register(request: RegisterRequest) {
        return client.post("auth/register", request)
    }
}
