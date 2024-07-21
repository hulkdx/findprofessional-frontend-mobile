package com.hulkdx.findprofessional.feature.utils

import com.hulkdx.findprofessional.core.model.user.Token
import com.hulkdx.findprofessional.core.model.user.UserData


fun newUserData(accessToken: String = "non empty", refreshToken: String = "non empty") = UserData(
    Token(accessToken, refreshToken),
    newUser()
)
