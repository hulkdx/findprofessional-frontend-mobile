package com.hulkdx.findprofessional.app.di.module

import com.hulkdx.findprofessional.app.config.api.FindProfessionalApiFactory
import com.hulkdx.findprofessional.core.config.PlatformSpecific
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.Module
import org.koin.dsl.module

val apiModule: Module
    get() = module {
        factory<HttpClientConfig<*>.() -> Unit> {
            {
                val ps = get<PlatformSpecific>()
                val baseUrl = FindProfessionalApiFactory.baseUrl(ps)

                install(ContentNegotiation) {
                    json()
                }
                install(DefaultRequest) {
                    url(baseUrl)
                }
                // throws exception when request is not successful:
                expectSuccess = true

                if (ps.isDebug()) {
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
        }
        single {
            HttpClient(get<HttpClientConfig<*>.() -> Unit>())
        }

    }
