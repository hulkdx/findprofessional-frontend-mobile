package com.hulkdx.findprofessional.feature.authentication.login.storage.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.hulkdx.findprofessional.core.storage.getAsSerializable
import com.hulkdx.findprofessional.core.storage.removeString
import com.hulkdx.findprofessional.core.storage.setAsSerializable
import com.hulkdx.findprofessional.feature.authentication.login.model.UserData
import com.hulkdx.findprofessional.feature.authentication.login.storage.UserStorage


class UserStorageDataStore(
    private val dataStore: DataStore<Preferences>,
) : UserStorage {
    private val key = "UserStorageDataStore"

    override suspend fun get() = dataStore.getAsSerializable<UserData>(key)
    override suspend fun set(value: UserData) = dataStore.setAsSerializable(key, value)
    override suspend fun remove() = dataStore.removeString(key)
}
