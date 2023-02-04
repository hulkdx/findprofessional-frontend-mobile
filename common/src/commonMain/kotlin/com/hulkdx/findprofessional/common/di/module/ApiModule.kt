package com.hulkdx.findprofessional.common.di.module

import com.hulkdx.findprofessional.common.config.api.BaseUrl
import com.hulkdx.findprofessional.common.config.isDebug
import com.hulkdx.findprofessional.common.config.localhost
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.core.module.Module
import org.koin.dsl.module

val apiModule: Module
    get() = module {
        single {
            val url = if (isDebug()) {
                "http://${localhost()}:8080/"
            } else {
                "http://api.sabajafarzadeh.com:30000/"
            }
            BaseUrl(url)
        }

        single {
            val baseUrl = get<BaseUrl>()

            HttpClient {
                install(ContentNegotiation) {
                    json()
                }
                install(DefaultRequest) {
                    url(baseUrl.value)
                }
                expectSuccess = true
            }
        }
    }
