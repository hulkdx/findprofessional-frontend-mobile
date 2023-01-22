package com.hulkdx.findprofessional.common.di.module

import io.ktor.client.*
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val apiModule: Module
    get() = module {
        singleOf<HttpClient>(::HttpClient)
    }
