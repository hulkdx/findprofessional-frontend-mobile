package com.hulkdx.findprofessional.common.di.module

import com.hulkdx.findprofessional.common.config.PlatformSpecific
import com.hulkdx.findprofessional.common.config.api.BaseUrl
import com.hulkdx.findprofessional.common.config.api.interceptor.AppInterceptor
import com.hulkdx.findprofessional.common.config.api.interceptor.TokenInterceptor
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val apiModule: Module
    get() = module {
        single {
            val ps = get<PlatformSpecific>()
            val url = if (ps.isDebug()) {
                "http://${ps.localhostUrl()}:8080/"
            } else {
                "http://api.sabajafarzadeh.com:30000/"
            }
            BaseUrl(url)
        }

        single {
            val baseUrl = get<BaseUrl>()
            val ps = get<PlatformSpecific>()

            HttpClient {
                install(ContentNegotiation) {
                    json()
                }
                install(DefaultRequest) {
                    url(baseUrl.value)
                }
                // throws exception when request is not successful:
                expectSuccess = true

                if (ps.isDebug()) {
                    install(Logging) {
                        logger = object : Logger {
                            override fun log(message: String) {
                                println(message)
                            }
                        }
                        level = LogLevel.ALL
                    }
                }
            }.apply {
                val interceptors = getAll<AppInterceptor>()
                plugin(HttpSend).apply {
                    for (interceptor in interceptors) {
                        intercept(interceptor::intercept)
                    }
                }
            }
        }

        provideInterceptors()
    }

private inline fun Module.provideInterceptors() {
    factoryOf<AppInterceptor>(::TokenInterceptor)
}
