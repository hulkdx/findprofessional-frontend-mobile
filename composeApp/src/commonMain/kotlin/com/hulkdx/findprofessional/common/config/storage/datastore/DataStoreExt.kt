package com.hulkdx.findprofessional.common.config.storage.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <T> DataStore<Preferences>.getFlow(pref: Preferences.Key<T>) =
    data.map { it[pref] }

fun DataStore<Preferences>.getFlowAsString(key: String) =
    getFlow(stringPreferencesKey(key))

suspend fun DataStore<Preferences>.getAsString(key: String) =
    getFlowAsString(key).firstOrNull()

fun DataStore<Preferences>.getFlowAsBoolean(key: String) =
    getFlow(booleanPreferencesKey(key))

suspend fun DataStore<Preferences>.getAsBoolean(key: String) =
    getFlowAsBoolean(key).firstOrNull()

suspend inline fun <reified T> DataStore<Preferences>.getAsSerializable(key: String): T? {
    val str = getAsString(key) ?: return null
    return Json.decodeFromString<T>(str)
}

suspend inline fun <T> DataStore<Preferences>.set(pref: Preferences.Key<T>, value: T) {
    edit { it[pref] = value }
}

suspend fun DataStore<Preferences>.setAsString(key: String, value: String) =
    set(stringPreferencesKey(key), value)

suspend fun DataStore<Preferences>.setAsBoolean(key: String, value: Boolean) =
    set(booleanPreferencesKey(key), value)

suspend inline fun <reified T> DataStore<Preferences>.setAsSerializable(key: String, value: T) {
    val str = Json.encodeToString(value)
    setAsString(key, str)
}

suspend fun <T> DataStore<Preferences>.remove(pref: Preferences.Key<T>) {
    edit { it.remove(pref) }
}

suspend fun DataStore<Preferences>.removeString(key: String) = remove(stringPreferencesKey(key))

suspend fun DataStore<Preferences>.removeBoolean(key: String) = remove(booleanPreferencesKey(key))
