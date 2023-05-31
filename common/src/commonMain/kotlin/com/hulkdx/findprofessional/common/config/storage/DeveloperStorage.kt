package com.hulkdx.findprofessional.common.config.storage

import kotlinx.coroutines.flow.Flow

interface DeveloperStorage {
    fun getAsFlowBoolean(key: Key): Flow<Boolean?>
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
