package com.hulkdx.findprofessional.core.utils

import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.core.storage.getAccessToken
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.bearerAuth

suspend fun HttpRequestBuilder.auth(userStorage: UserStorage) {
    bearerAuth(userStorage.getAccessToken())
}
