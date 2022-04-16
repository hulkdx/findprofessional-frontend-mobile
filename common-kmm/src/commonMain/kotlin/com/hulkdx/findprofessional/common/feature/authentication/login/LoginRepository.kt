package com.hulkdx.findprofessional.common.feature.authentication.login

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class LoginRepository {
    fun getFlow(): Flow<String> = flow {
        emit("1")
        delay(2000)
        emit("2")
    }
}
