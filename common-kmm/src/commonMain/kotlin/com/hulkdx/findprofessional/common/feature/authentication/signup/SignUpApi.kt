package com.hulkdx.findprofessional.common.feature.authentication.signup

import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.common.utils.post
import io.ktor.client.*


class SignUpApi(
    private val client: HttpClient,
) {
    // TODO: change baseUrl for debug, prod
    private val baseUrl = "http://localhost:8080"
    private val baseUrlAndroid = "http://10.0.2.2:8080"

    suspend fun register(request: RegisterRequest) {
        return client.post("$baseUrl/auth/register", request)
    }
}
