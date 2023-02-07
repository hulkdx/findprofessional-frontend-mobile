package com.hulkdx.findprofessional.common.feature.authentication.signup

import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.common.utils.post
import io.ktor.client.*


interface SignUpApi {
    suspend fun register(request: RegisterRequest)
}

class SignUpApiImpl (
    private val client: HttpClient,
): SignUpApi {

    override suspend fun register(request: RegisterRequest) {
        return client.post("auth/register", request)
    }

}
