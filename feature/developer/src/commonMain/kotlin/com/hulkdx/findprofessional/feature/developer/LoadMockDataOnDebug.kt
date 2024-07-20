package com.hulkdx.findprofessional.feature.developer

import com.hulkdx.findprofessional.core.config.isDebug
import com.hulkdx.findprofessional.feature.authentication.login.storage.DeveloperStorage
import com.hulkdx.findprofessional.feature.authentication.login.storage.isMockData
import org.koin.mp.KoinPlatformTools


fun loadMockDataOnDebug() {
    if (isDebug()) {
        val d: DeveloperStorage = KoinPlatformTools.defaultContext().get().get()
        if (d.isMockData()) {
            val inMemoryApi: InMemoryApi = KoinPlatformTools.defaultContext().get().get()
            inMemoryApi.loadKoinModules()
        }
    }
}
