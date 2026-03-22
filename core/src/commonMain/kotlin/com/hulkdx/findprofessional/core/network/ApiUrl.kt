package com.hulkdx.findprofessional.core.network

import com.hulkdx.findprofessional.core.platform.isDebug
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.url

private val impl: ApiUrl = if (isDebug()) DebugApiUrl else ProdApiUrl

fun HttpRequestBuilder.apiUrl(urlString: String) {
    url(urlString = impl.of(urlString))
}

const val PROD_BASE_URL = "https://api.findprofessionalapp.com"

interface ApiUrl {
    fun of(path: String): String
}
