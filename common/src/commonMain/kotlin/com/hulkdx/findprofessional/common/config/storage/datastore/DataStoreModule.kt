package com.hulkdx.findprofessional.common.config.storage.datastore

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.hulkdx.findprofessional.common.config.PlatformSpecific
import com.hulkdx.findprofessional.common.config.api.BaseUrl
import com.hulkdx.findprofessional.common.config.api.interceptor.AppInterceptor
import com.hulkdx.findprofessional.common.config.api.interceptor.TokenInterceptor
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import okio.Path.Companion.toPath
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

private const val FILE_NAME = "datastore_pref.preferences_pb"

val datastoreModule: Module
    get() = module {
        single {
            val platformSpecific = get<PlatformSpecific>()
            val appDirectoryPath = platformSpecific.appDirectoryPath()
            val file = "$appDirectoryPath/$FILE_NAME"
            PreferenceDataStoreFactory.createWithPath(produceFile = { file.toPath() })
        }
        factoryOf(::AccessTokenStorageDataStore)
        factoryOf(::RefreshTokenStorageDataStore)
    }
