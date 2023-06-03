package com.hulkdx.findprofessional.common.config.storage

import kotlinx.coroutines.flow.Flow
import org.koin.mp.KoinPlatformTools

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

suspend fun isMockData(): Boolean {
    val d: DeveloperStorage = KoinPlatformTools.defaultContext().get().get()
    return d.getAsBoolean(DeveloperStorage.Key.MockData)
}
