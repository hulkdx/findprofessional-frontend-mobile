package com.hulkdx.findprofessional.app.config.api

import com.hulkdx.findprofessional.core.platform.PlatformSpecific
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

object AppApiProvider {

    fun httpClient(ps: PlatformSpecific): HttpClient {
        return HttpClient(getConfig(ps))
    }

    fun getConfig(ps: PlatformSpecific): HttpClientConfig<*>.() -> Unit = {
        install(ContentNegotiation) {
            json()
        }

        install(HttpTimeout) {
            socketTimeoutMillis = 10_000
            requestTimeoutMillis =  10_000
            connectTimeoutMillis = 10_000
        }

        // throws exception when request is not successful:
        expectSuccess = true

        if (ps.isDebug()) {
            debugClientConfig()
        }
    }

    private fun HttpClientConfig<*>.debugClientConfig() {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("--------------")
                    println(message)
                }
            }
            level = LogLevel.ALL
        }
    }
}
