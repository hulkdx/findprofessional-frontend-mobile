package com.hulkdx.findprofessional.common.config.api.interceptor

import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

class TokenInterceptor: AppInterceptor {
    override suspend fun intercept(sender: Sender, request: HttpRequestBuilder): HttpClientCall {
        val original = sender.execute(request)
        return original
    }
}
