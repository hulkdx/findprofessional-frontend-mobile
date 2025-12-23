package com.hulkdx.findprofessional.feature.authentication.login

import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.feature.authentication.login.api.LoginApi
import com.hulkdx.findprofessional.feature.authentication.login.api.LoginApiImpl
import com.hulkdx.findprofessional.feature.authentication.login.api.RefreshTokenApi
import com.hulkdx.findprofessional.feature.authentication.login.api.RefreshTokenApiImpl
import com.hulkdx.findprofessional.feature.authentication.login.storage.datastore.UserStorageDataStore
import com.hulkdx.findprofessional.feature.authentication.login.usecase.GetUserUseCase
import com.hulkdx.findprofessional.feature.authentication.login.usecase.LogoutUseCase
import com.hulkdx.findprofessional.feature.authentication.login.usecase.LogoutUseCaseImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val loginModule: Module
    get() = module {
        factoryOf(::GetUserUseCase)
        factoryOf(::UserStorageDataStore) bind UserStorage::class
        viewModelOf(::LoginViewModel)
        factoryOf(::LoginUseCase)
        factoryOf(::LogoutUseCaseImpl) bind LogoutUseCase::class
        factoryOf(::LoginApiImpl) bind LoginApi::class
        factoryOf(::RefreshTokenApiImpl) bind RefreshTokenApi::class
    }
