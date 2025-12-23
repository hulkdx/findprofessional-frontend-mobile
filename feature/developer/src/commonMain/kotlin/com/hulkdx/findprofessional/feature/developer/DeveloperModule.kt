package com.hulkdx.findprofessional.feature.developer

import com.hulkdx.findprofessional.feature.developer.storage.DeveloperStorage
import com.hulkdx.findprofessional.feature.developer.storage.DeveloperStorageDataStore
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val developerModule: Module
    get() = module {
        singleOf(::InMemoryApiImpl) bind InMemoryApi::class
        factoryOf(::DeveloperStorageDataStore) bind DeveloperStorage::class
    }
