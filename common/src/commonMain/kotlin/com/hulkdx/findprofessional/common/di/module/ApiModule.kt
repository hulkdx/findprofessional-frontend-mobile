package com.hulkdx.findprofessional.common.di.module

import com.hulkdx.findprofessional.common.config.PlatformSpecific
import com.hulkdx.findprofessional.common.config.api.FindProfessionalApiFactory
import com.hulkdx.findprofessional.common.config.api.interceptor.AppInterceptor
import com.hulkdx.findprofessional.common.config.api.interceptor.TokenInterceptor
import com.hulkdx.findprofessional.common.feature.authentication.login.RefreshTokenApiImpl
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.core.module.Module
import org.koin.dsl.module

val apiModule: Module
    get() = module {
        single {
            val ps = get<PlatformSpecific>()
            val baseUrl = FindProfessionalApiFactory.baseUrl(ps)

            HttpClient {
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
                                println(message)
                            }
                        }
                        level = LogLevel.ALL
                    }
                }
            }.apply {
                val tokenInterceptor = TokenInterceptor(
                    RefreshTokenApiImpl(this),
                    get(),
                    get(),
                )
                val interceptors = listOf<AppInterceptor>(
                    tokenInterceptor,
                )
                plugin(HttpSend).apply {
                    for (interceptor in interceptors) {
                        intercept(interceptor::intercept)
                    }
                }
            }
        }
    }
