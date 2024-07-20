package com.hulkdx.findprofessional.app.di.module

import com.hulkdx.findprofessional.app.config.api.InMemoryApiImpl
import com.hulkdx.findprofessional.core.config.isDebug
import com.hulkdx.findprofessional.feature.authentication.login.storage.DeveloperStorage
import com.hulkdx.findprofessional.feature.authentication.login.storage.datastore.DeveloperStorageDataStore
import com.hulkdx.findprofessional.feature.developer.InMemoryApi
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.ModuleDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module


fun debugModule() = provideDebugModule {
    singleOf(::InMemoryApiImpl) bind InMemoryApi::class
    factoryOf(::DeveloperStorageDataStore) bind DeveloperStorage::class
}

fun provideDebugModule(moduleDeclaration: ModuleDeclaration): Module {
    return if (isDebug()) module(moduleDeclaration = moduleDeclaration)
    else module {}
}
