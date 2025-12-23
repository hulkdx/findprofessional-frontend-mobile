package com.hulkdx.findprofessional.feature.pro.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.hulkdx.findprofessional.feature.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.storage.getFlow
import com.hulkdx.findprofessional.core.storage.getFlowAsSerializable
import com.hulkdx.findprofessional.core.storage.removeString
import com.hulkdx.findprofessional.core.storage.setAsSerializable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

interface AvailabilityStorage {
    fun getFlow(): Flow<List<ProfessionalAvailability>>
    suspend fun set(value: List<ProfessionalAvailability>)
    suspend fun remove()
}

class AvailabilityStorageDataStore(
    private val dataStore: DataStore<Preferences>,
) : AvailabilityStorage {
    private val key = "AvailabilityStorageDataStore"

    override fun getFlow() = dataStore.getFlowAsSerializable<List<ProfessionalAvailability>>(key)
        .map { it ?: emptyList() }

    override suspend fun set(value: List<ProfessionalAvailability>) =
        dataStore.setAsSerializable(key, value)

    override suspend fun remove() = dataStore.removeString(key)
}
