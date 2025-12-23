package com.hulkdx.findprofessional.feature.authentication.login

import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.core.utils.generalError
import com.hulkdx.findprofessional.feature.authentication.login.api.LoginApi
import com.hulkdx.findprofessional.feature.authentication.login.model.LoginRequest

class LoginUseCase(
    private val api: LoginApi,
    private val userStorage: UserStorage,
) {
    suspend fun login(request: LoginRequest) = try {
        val userData = api.login(request)
        userStorage.set(userData)
        null to userData
    } catch (e: Throwable) {
        e.generalError() to null
    }
}
