package com.hulkdx.findprofessional.app.test.utils

import com.hulkdx.findprofessional.core.model.user.ProUser
import com.hulkdx.findprofessional.core.model.user.Token
import com.hulkdx.findprofessional.core.model.user.User
import com.hulkdx.findprofessional.core.model.user.UserData
import com.hulkdx.findprofessional.core.model.user.UserType
import com.hulkdx.findprofessional.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.feature.developer.InMemoryApi

val inMemoryApi by lazy { get<InMemoryApi>() }

fun setUser(email: String, password: String) {
    setUser(User(email = email, firstName = "", lastName = ""))
}

fun setProUser(email: String, password: String) {
    setUser(ProUser(email = email))
}

fun setUser(user: UserType) {
    inMemoryApi.setUserData(
        UserData(
            Token(
                "uiTestAccessToken",
                "uiTestRefreshToken",
            ),
            user,
        )
    )
}
