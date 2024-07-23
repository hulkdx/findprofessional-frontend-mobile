package com.hulkdx.findprofessional.core.api

import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.core.utils.AccessTokenNotFoundException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.bearerAuth

suspend fun HttpRequestBuilder.auth(userStorage: UserStorage) {
    val accessToken = userStorage.get()?.token?.accessToken ?: throw AccessTokenNotFoundException()
    bearerAuth(accessToken)
}
