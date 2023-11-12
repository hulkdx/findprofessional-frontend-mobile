package com.hulkdx.findprofessional

import android.app.Application
import com.hulkdx.findprofessional.common.config.storage.isMockData
import kotlinx.coroutines.runBlocking
import org.koin.core.context.loadKoinModules


fun Application.debugOnCreate() {
    runBlocking {
        if (isMockData()) {
            loadKoinModules(InMemoryApi.module)
        }
    }
}
