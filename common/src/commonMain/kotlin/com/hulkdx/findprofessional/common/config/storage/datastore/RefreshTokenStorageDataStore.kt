package com.hulkdx.findprofessional.common.config.storage.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.hulkdx.findprofessional.common.config.storage.RefreshTokenStorage

class RefreshTokenStorageDataStore(
    private val dataStore: DataStore<Preferences>,
) : RefreshTokenStorage {
    private val key = "AccessTokenStorageDataStore"

    override suspend fun get() = dataStore.getAsString(key)
    override suspend fun set(value: String) = dataStore.set(key, value)
    override suspend fun remove() = dataStore.remove(key)
}
