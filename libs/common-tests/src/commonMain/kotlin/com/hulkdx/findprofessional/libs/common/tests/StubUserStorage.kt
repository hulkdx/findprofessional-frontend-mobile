package com.hulkdx.findprofessional.libs.common.tests

import com.hulkdx.findprofessional.feature.authentication.model.user.UserData
import com.hulkdx.findprofessional.feature.authentication.storage.UserStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class StubUserStorage : UserStorage {
    override suspend fun get(): UserData? = null
    override suspend fun set(value: UserData) {}
    override suspend fun remove() {}
    override fun getFlow(): Flow<UserData?> = flow {}
}
