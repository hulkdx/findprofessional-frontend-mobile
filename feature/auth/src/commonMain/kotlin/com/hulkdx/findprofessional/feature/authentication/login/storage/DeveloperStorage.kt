package com.hulkdx.findprofessional.feature.authentication.login.storage

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

interface DeveloperStorage {
    fun getAsFlowBoolean(key: Key): Flow<Boolean?>
    suspend fun getAsBoolean(key: Key): Boolean
    suspend fun setAsBoolean(key: Key, value: Boolean)
    suspend fun remove(key: Key)

    enum class Key {
        MockData,
        ;

        override fun toString(): String {
            return "DeveloperStorage${super.toString()}"
        }
    }
}

fun DeveloperStorage.isMockData() = runBlocking {
    getAsBoolean(DeveloperStorage.Key.MockData)
}
