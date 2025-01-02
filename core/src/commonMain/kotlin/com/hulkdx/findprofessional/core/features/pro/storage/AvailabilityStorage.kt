package com.hulkdx.findprofessional.core.features.pro.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.storage.getAsSerializable
import com.hulkdx.findprofessional.core.storage.removeString
import com.hulkdx.findprofessional.core.storage.setAsSerializable

interface AvailabilityStorage {
    suspend fun get(): List<ProfessionalAvailability>?
    suspend fun set(value: List<ProfessionalAvailability>)
    suspend fun remove()
}

class AvailabilityStorageDataStore(
    private val dataStore: DataStore<Preferences>,
) : AvailabilityStorage {
    private val key = "AvailabilityStorageDataStore"

    override suspend fun get() = dataStore.getAsSerializable<List<ProfessionalAvailability>>(key)

    override suspend fun set(value: List<ProfessionalAvailability>) =
        dataStore.setAsSerializable(key, value)

    override suspend fun remove() = dataStore.removeString(key)
}
