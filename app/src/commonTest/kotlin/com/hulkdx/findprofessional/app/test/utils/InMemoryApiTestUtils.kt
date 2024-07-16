package com.hulkdx.findprofessional.app.test.utils

import com.hulkdx.findprofessional.app.config.api.InMemoryApi
import com.hulkdx.findprofessional.feature.authentication.signup.model.RegisterRequest

val inMemoryApi by lazy { get<InMemoryApi>() }

fun setUser(email: String, password: String) {
    inMemoryApi.setUser(
        RegisterRequest(email, password, firstName = "", lastName = "")
    )
}
