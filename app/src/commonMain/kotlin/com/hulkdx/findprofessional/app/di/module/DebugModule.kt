package com.hulkdx.findprofessional.app.di.module

import com.hulkdx.findprofessional.core.platform.isDebug
import com.hulkdx.findprofessional.feature.developer.developerModule
import org.koin.core.module.Module


fun debugModules(): Array<Module> {
    return if (isDebug()) {
        arrayOf(
            developerModule,
        )
    } else {
        emptyArray()
    }
}
