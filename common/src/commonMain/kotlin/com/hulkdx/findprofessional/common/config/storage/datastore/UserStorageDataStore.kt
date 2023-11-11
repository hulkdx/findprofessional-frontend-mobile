package com.hulkdx.findprofessional.common.config.storage.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.hulkdx.findprofessional.common.config.storage.UserStorage
import com.hulkdx.findprofessional.common.feature.authentication.model.User

class UserStorageDataStore(
    private val dataStore: DataStore<Preferences>,
) : UserStorage {
    private val key = "UserStorageDataStore"

    override suspend fun get() = dataStore.getAsSerializable<User>(key)
    override suspend fun set(value: User) = dataStore.setAsSerializable(key, value)
    override suspend fun remove() = dataStore.removeString(key)
}
