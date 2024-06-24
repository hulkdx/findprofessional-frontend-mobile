package com.hulkdx.findprofessional.common.config.api.interceptor

import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

interface AppInterceptor {
    suspend fun intercept(sender: Sender, request: HttpRequestBuilder): HttpClientCall
}
