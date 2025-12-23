package com.hulkdx.findprofessional.feature.authentication.login.api

import com.hulkdx.findprofessional.feature.authentication.login.model.LoginRequest
import com.hulkdx.findprofessional.feature.authentication.model.user.UserData

interface LoginApi {
    suspend fun login(request: LoginRequest): UserData
}
