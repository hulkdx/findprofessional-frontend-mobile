package com.hulkdx.findprofessional.common.di.module

import com.hulkdx.findprofessional.common.config.PlatformSpecific
import com.hulkdx.findprofessional.common.config.api.FindProfessionalApiFactory
import com.hulkdx.findprofessional.common.config.api.interceptor.AppInterceptor
import com.hulkdx.findprofessional.common.config.api.interceptor.TokenInterceptor
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
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

// Needs to be done after startKoin so we can access koin
// Needs to be done after HttpClient provide to avoid circular dependency
fun setupApiInterceptors() {
    val koin = KoinPlatformTools.defaultContext().get()
    val httpClient: HttpClient = koin.get()
    val interceptors: List<AppInterceptor> = koin.getAll()
    httpClient.plugin(HttpSend).apply {
        for (interceptor in interceptors) {
            intercept(interceptor::intercept)
        }
    }
}

private inline fun Module.provideInterceptors() {
    factoryOf(::TokenInterceptor) bind AppInterceptor::class
}
