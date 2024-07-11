package com.hulkdx.findprofessional.feature.authentication.login

import com.hulkdx.findprofessional.core.utils.generalError
import com.hulkdx.findprofessional.feature.authentication.login.model.LoginRequest
import com.hulkdx.findprofessional.feature.authentication.login.storage.UserStorage

class LoginUseCase(
    private val api: LoginApi,
    private val userStorage: UserStorage,
) {
    suspend fun login(request: LoginRequest) = try {
        val userData = api.login(request)
        userStorage.set(userData)
        null
    } catch (e: Throwable) {
        e.generalError()
    }
}
