package com.hulkdx.findprofessional.feature.developer

import com.hulkdx.findprofessional.core.platform.isDebug
import com.hulkdx.findprofessional.feature.developer.storage.DeveloperStorage
import com.hulkdx.findprofessional.feature.developer.storage.isMockData
import org.koin.mp.KoinPlatformTools


fun loadMockDataOnDebug() {
    if (isDebug()) {
        if (get<DeveloperStorage>().isMockData()) {
            get<InMemoryApi>().loadKoinModules()
        }
    }
}

private inline fun <reified T : Any> get() = KoinPlatformTools.defaultContext().get().get<T>()
