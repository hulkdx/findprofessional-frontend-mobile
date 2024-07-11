package com.hulkdx.findprofessional.feature.authentication.login

import com.hulkdx.findprofessional.feature.authentication.login.storage.UserStorage
import com.hulkdx.findprofessional.feature.authentication.login.storage.datastore.UserStorageDataStore
import com.hulkdx.findprofessional.feature.authentication.login.usecase.GetUserUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val loginModule: Module
    get() = module {
        factoryOf(::GetUserUseCase)
        factoryOf(::UserStorageDataStore) bind UserStorage::class
        factoryOf(::LoginViewModel)
        factoryOf(::LoginUseCase)
        factoryOf(::LoginApiImpl) bind LoginApi::class
    }
