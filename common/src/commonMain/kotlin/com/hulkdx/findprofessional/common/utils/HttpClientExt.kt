package com.hulkdx.findprofessional.common.utils

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

internal suspend inline fun <reified T, reified R> HttpClient.post(url: String, request: T): R {
    return post {
        url(url)
        contentType(ContentType.Application.Json)
        setBody(request)
    }
        .body()
}
