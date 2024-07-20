package com.hulkdx.findprofessional.app.test.utils

import com.hulkdx.findprofessional.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.feature.developer.InMemoryApi

val inMemoryApi by lazy { get<InMemoryApi>() }

fun setUser(email: String, password: String) {
    inMemoryApi.setUser(
        RegisterRequest(email, password, firstName = "", lastName = "")
    )
}
