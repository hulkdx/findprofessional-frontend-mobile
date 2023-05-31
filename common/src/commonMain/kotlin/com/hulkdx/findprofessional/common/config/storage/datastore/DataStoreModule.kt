package com.hulkdx.findprofessional.common.config.storage.datastore

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.hulkdx.findprofessional.common.config.PlatformSpecific
import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.config.storage.DeveloperStorage
import com.hulkdx.findprofessional.common.config.storage.RefreshTokenStorage
import okio.Path.Companion.toPath
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
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
        factoryOf(::AccessTokenStorageDataStore) bind AccessTokenStorage::class
        factoryOf(::RefreshTokenStorageDataStore) bind RefreshTokenStorage::class
        factoryOf(::DeveloperStorageDataStore) bind DeveloperStorage::class
    }
