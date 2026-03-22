package com.hulkdx.findprofessional.core.network

interface DebugBaseUrlProvider {
    fun baseUrl(path: String): String
}
