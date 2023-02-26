package com.hulkdx.findprofessional.common.config.storage.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.singleOrNull

fun DataStore<Preferences>.getFlowAsString(key: String) =
    data.map { it[stringPreferencesKey(key)] }

suspend fun DataStore<Preferences>.getAsString(key: String) =
    data.map { it[stringPreferencesKey(key)] }.singleOrNull()

suspend fun DataStore<Preferences>.set(key: String, value: String) {
    edit { it[stringPreferencesKey(key)] = value }
}
