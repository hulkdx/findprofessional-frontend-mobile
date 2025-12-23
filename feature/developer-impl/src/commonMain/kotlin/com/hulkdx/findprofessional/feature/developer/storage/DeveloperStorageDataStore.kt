package com.hulkdx.findprofessional.feature.developer.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.hulkdx.findprofessional.core.storage.getAsBoolean
import com.hulkdx.findprofessional.core.storage.getFlow
import com.hulkdx.findprofessional.core.storage.removeBoolean
import com.hulkdx.findprofessional.core.storage.setAsBoolean
import com.hulkdx.findprofessional.feature.developer.storage.DeveloperStorage.Key


class DeveloperStorageDataStore(
    private val dataStore: DataStore<Preferences>,
) : DeveloperStorage {

    override fun getAsFlowBoolean(key: Key) =
        dataStore.getFlow(booleanPreferencesKey(key.toString()))

    override suspend fun getAsBoolean(key: Key) =
        dataStore.getAsBoolean(key.toString()) ?: false

    override suspend fun setAsBoolean(key: Key, value: Boolean) =
        dataStore.setAsBoolean(key.toString(), value)

    override suspend fun remove(key: Key) =
        dataStore.removeBoolean(key.toString())
}
