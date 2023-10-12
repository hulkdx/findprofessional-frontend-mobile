package com.hulkdx.findprofessional.common.utils

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

suspend inline fun <reified T, reified R> HttpClient.post(
    url: String,
    request: T
): R {
    return post {
        url(url)
        contentType(ContentType.Application.Json)
        setBody(request)
    }
        .body()
}

suspend inline fun <reified R> HttpClient.get(
    url: String,
): R {
    return get {
        url(url)
        contentType(ContentType.Application.Json)
    }
        .body()
}

suspend inline fun <reified R> HttpClient.getAuth(
    accessTokenStorage: AccessTokenStorage,
    url: String,
): R {
    return get {
        url(url)
        contentType(ContentType.Application.Json)
        setAccessToken(accessTokenStorage)
    }
        .body()
}

suspend inline fun HttpRequestBuilder.setAccessToken(accessTokenStorage: AccessTokenStorage) {
    val accessToken = accessTokenStorage.get() ?: throw AccessTokenNotFoundException()
    bearerAuth(accessToken)
}
