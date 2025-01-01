package com.hulkdx.findprofessional.app.di.module

import com.hulkdx.findprofessional.app.config.api.AppApiProvider
import com.hulkdx.findprofessional.feature.authentication.login.api.TokenInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.mp.KoinPlatformTools

val apiModule: Module
    get() = module {
        single { AppApiProvider.httpClient(get()) }

        factoryOf(::TokenInterceptor)
    }

// Needs to be done after startKoin so we can access koin
// Needs to be done after HttpClient provide to avoid circular dependency
fun setupApiInterceptors() {
    val koin = KoinPlatformTools.defaultContext().get()
    val httpClient: HttpClient = koin.get()
    val tokenInterceptor: TokenInterceptor = koin.get()
    httpClient.plugin(HttpSend).apply {
        intercept(tokenInterceptor::intercept)
    }
}
