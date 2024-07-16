package com.hulkdx.findprofessional.app.di.module

import com.hulkdx.findprofessional.app.config.api.InMemoryApi
import com.hulkdx.findprofessional.app.config.api.InMemoryApiImpl
import com.hulkdx.findprofessional.core.config.isDebug
import org.koin.core.module.Module
import org.koin.dsl.ModuleDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module


fun debugModule() = provideDebugModule {
    factory { InMemoryApiImpl() } bind InMemoryApi::class
}

fun provideDebugModule(moduleDeclaration: ModuleDeclaration): Module {
    return if (isDebug()) module(moduleDeclaration = moduleDeclaration)
    else module {}
}
