package com.hulkdx.findprofessional.app.test.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.runBlocking


fun deleteDataStoreFile() {
    runBlocking { get<DataStore<Preferences>>().edit { it.clear() } }
}
