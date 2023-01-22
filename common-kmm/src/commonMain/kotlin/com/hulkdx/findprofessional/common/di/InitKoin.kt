package com.hulkdx.findprofessional.common.di

import com.hulkdx.findprofessional.common.di.module.apiModule
import com.hulkdx.findprofessional.common.di.module.repositoryModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    appDeclaration: KoinAppDeclaration
) {
    startKoin {
        appDeclaration()
        modules(
            repositoryModule,
            apiModule,
            platformModule()
        )
    }
}
