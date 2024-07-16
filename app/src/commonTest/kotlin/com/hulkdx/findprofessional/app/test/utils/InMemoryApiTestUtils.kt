package com.hulkdx.findprofessional.app.test.utils

import com.hulkdx.findprofessional.app.config.api.InMemoryApi
import com.hulkdx.findprofessional.feature.authentication.signup.model.RegisterRequest


fun setUser(email: String, password: String) {
    get<InMemoryApi>().setUser(
        RegisterRequest(email, password, firstName = "", lastName = "")
    )
}
