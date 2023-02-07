package com.hulkdx.findprofessional

import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpApi
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest

object InMemoryApi {
    var user: RegisterRequest? = null

    object Signup: SignUpApi {
        override suspend fun register(request: RegisterRequest) {
            user = request
        }
    }
}
