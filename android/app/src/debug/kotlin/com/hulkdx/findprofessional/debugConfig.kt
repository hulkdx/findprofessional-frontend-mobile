package com.hulkdx.findprofessional

import com.hulkdx.findprofessional.common.config.storage.isMockData
import kotlinx.coroutines.runBlocking
import org.koin.core.context.loadKoinModules


fun MainApplication.debugOnCreate() {
    runBlocking {
        if (isMockData()) {
            loadKoinModules(InMemoryApi.module)
        }
    }
}
