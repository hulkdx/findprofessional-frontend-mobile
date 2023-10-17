package com.hulkdx.findprofessional.common.utils

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

suspend inline fun HttpRequestBuilder.auth(accessTokenStorage: AccessTokenStorage) {
    val accessToken = accessTokenStorage.get() ?: throw AccessTokenNotFoundException()
    bearerAuth(accessToken)
}
