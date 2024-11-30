package com.hulkdx.findprofessional.feature.pro.auth.signup.usecase

import com.hulkdx.findprofessional.core.model.proauth.SignUpProRequest
import com.hulkdx.findprofessional.core.model.user.ProUser
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.somethingWentWrong
import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.core.utils.generalError
import com.hulkdx.findprofessional.core.utils.toStringOrRes
import com.hulkdx.findprofessional.feature.pro.auth.signup.api.SignUpProApi

class SignUpProUseCase(
    private val api: SignUpProApi,
    private val userStorage: UserStorage,
) {
    suspend fun register(request: SignUpProRequest): StringOrRes? = try {
        val userData = api.register(request)
        if (userData.user is ProUser) {
            userStorage.set(userData)
            null
        } else {
            Res.string.somethingWentWrong.toStringOrRes()
        }
    } catch (e: Exception) {
        e.generalError()
    }
}
