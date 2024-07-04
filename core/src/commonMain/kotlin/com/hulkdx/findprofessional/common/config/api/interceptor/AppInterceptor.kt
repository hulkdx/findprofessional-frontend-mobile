package com.hulkdx.findprofessional.common.config.api.interceptor

import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.Sender
import io.ktor.client.request.HttpRequestBuilder

interface AppInterceptor {
    suspend fun intercept(sender: Sender, request: HttpRequestBuilder): HttpClientCall
}
