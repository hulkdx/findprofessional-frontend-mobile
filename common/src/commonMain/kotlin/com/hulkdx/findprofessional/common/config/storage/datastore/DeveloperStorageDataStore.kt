package com.hulkdx.findprofessional.common.config.storage.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.hulkdx.findprofessional.common.config.storage.DeveloperStorage
import com.hulkdx.findprofessional.common.config.storage.DeveloperStorage.Key
import kotlinx.coroutines.flow.Flow


class DeveloperStorageDataStore(
    private val dataStore: DataStore<Preferences>,
): DeveloperStorage {
    override fun getAsFlowBoolean(key: Key) = dataStore.getFlow(booleanPreferencesKey(key.toString()))
    override suspend fun setAsBoolean(key: Key, value: Boolean) = dataStore.setAsBoolean(key.toString(), value)
    override suspend fun remove(key: Key) = dataStore.removeBoolean(key.toString())
}
