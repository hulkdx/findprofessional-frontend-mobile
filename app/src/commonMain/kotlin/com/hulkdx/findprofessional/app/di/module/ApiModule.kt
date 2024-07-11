package com.hulkdx.findprofessional.app.di.module

import com.hulkdx.findprofessional.app.config.api.FindProfessionalApiFactory
import com.hulkdx.findprofessional.core.api.ApiInterceptor
import com.hulkdx.findprofessional.core.config.PlatformSpecific
import com.hulkdx.findprofessional.feature.authentication.login.api.TokenInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.mp.KoinPlatformTools

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

        provideInterceptors()
    }

private inline fun Module.provideInterceptors() {
    factoryOf(::TokenInterceptor) bind ApiInterceptor::class
}

// Needs to be done after startKoin so we can access koin
// Needs to be done after HttpClient provide to avoid circular dependency
fun setupApiInterceptors() {
    val koin = KoinPlatformTools.defaultContext().get()
    val httpClient: HttpClient = koin.get()
    val interceptors: List<ApiInterceptor> = koin.getAll()
    httpClient.plugin(HttpSend).apply {
        for (interceptor in interceptors) {
            intercept(interceptor::intercept)
        }
    }
}
