package com.hulkdx.findprofessional.feature.authentication.login.usecase

import com.hulkdx.findprofessional.feature.authentication.model.user.UserType

interface GetUserUseCase {
    suspend fun execute(): UserType?
}
