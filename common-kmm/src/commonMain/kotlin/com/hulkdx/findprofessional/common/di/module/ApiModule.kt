package com.hulkdx.findprofessional.common.di.module

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val apiModule: Module
    get() = module {
        single {
            HttpClient {
                install(ContentNegotiation) {
                    json()
                }
                expectSuccess = true
            }
        }
    }
