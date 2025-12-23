package com.hulkdx.findprofessional.feature.pro.auth

import com.hulkdx.findprofessional.feature.authentication.model.user.ProUser
import kotlinx.coroutines.flow.Flow

interface GetProUserUseCase {
    fun getUserFlow(): Flow<ProUser?>
    suspend fun getUser(): ProUser?
}
