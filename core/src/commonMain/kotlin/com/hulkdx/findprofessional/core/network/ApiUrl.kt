package com.hulkdx.findprofessional.core.network

import com.hulkdx.findprofessional.core.platform.PlatformSpecific
import com.hulkdx.findprofessional.core.platform.isDebug
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.url
import org.koin.core.component.KoinComponent
import org.koin.mp.KoinPlatformTools

fun HttpRequestBuilder.apiUrl(urlString: String) {
    url(urlString = ApiUrl.of(urlString))
}

private object ApiUrl : KoinComponent {

    private val isDebug = isDebug()

    private val baseUrl by lazy {
        if (isDebug) {
            val ps = KoinPlatformTools.defaultContext().get().get<PlatformSpecific>()
            "http://${ps.localhostUrl()}"
        } else {
            "http://api.sabajafarzadeh.com:3000"
        }
    }

    fun of(path: String): String {
        return "$baseUrl${getPort(path)}/$path"
    }

    private fun getPort(path: String): String {
        if (!isDebug) return ""
        return when (path) {
            "auth/login",
            "auth/signup",
            "auth/refresh-token",
            "auth/user",
                -> ":8080"

            "payments/create-intent" -> ":8082"

            else -> ":8081"
        }
    }
}

