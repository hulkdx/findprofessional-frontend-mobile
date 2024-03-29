package com.hulkdx.findprofessional.common.config.storage.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.hulkdx.findprofessional.common.config.storage.RefreshTokenStorage

class RefreshTokenStorageDataStore(
    private val dataStore: DataStore<Preferences>,
) : RefreshTokenStorage {
    private val key = "RefreshTokenStorageDataStore"

    override suspend fun get() = dataStore.getAsString(key)
    override suspend fun set(value: String) = dataStore.setAsString(key, value)
    override suspend fun remove() = dataStore.removeString(key)
}
