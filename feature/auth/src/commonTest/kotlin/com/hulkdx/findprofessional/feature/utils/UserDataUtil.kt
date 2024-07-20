package com.hulkdx.findprofessional.feature.utils

import com.hulkdx.findprofessional.feature.authentication.login.model.Token
import com.hulkdx.findprofessional.feature.authentication.login.model.UserData


fun newUserData(accessToken: String = "non empty", refreshToken: String = "non empty") = UserData(
    Token(accessToken, refreshToken),
    newUser()
)
