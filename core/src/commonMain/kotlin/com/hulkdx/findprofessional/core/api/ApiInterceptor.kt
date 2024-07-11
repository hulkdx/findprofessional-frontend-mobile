package com.hulkdx.findprofessional.core.api

import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.Sender
import io.ktor.client.request.HttpRequestBuilder

interface ApiInterceptor {
    suspend fun intercept(sender: Sender, request: HttpRequestBuilder): HttpClientCall
}
