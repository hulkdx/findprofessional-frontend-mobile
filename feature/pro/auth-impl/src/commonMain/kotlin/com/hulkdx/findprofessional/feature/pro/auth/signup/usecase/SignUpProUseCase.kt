package com.hulkdx.findprofessional.feature.pro.auth.signup.usecase

import com.hulkdx.findprofessional.core.features.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.core.features.pro.model.request.SignUpProRequest
import com.hulkdx.findprofessional.feature.authentication.model.user.ProUser
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.somethingWentWrong
import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.core.utils.generalError
import com.hulkdx.findprofessional.core.utils.toStringOrRes

class SignUpProUseCase(
    private val api: ProfessionalApi,
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
