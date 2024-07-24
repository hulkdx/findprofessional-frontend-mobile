package com.hulkdx.findprofessional.core.api

import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.core.utils.AccessTokenNotFoundException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.bearerAuth

suspend fun HttpRequestBuilder.auth(userStorage: UserStorage) {
    bearerAuth(userStorage.getAccessToken())
}

suspend fun UserStorage.getAccessToken() =
    get()?.token?.accessToken ?: throw AccessTokenNotFoundException()
