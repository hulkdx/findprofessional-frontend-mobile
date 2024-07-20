package com.hulkdx.findprofessional.app.di.module

import com.hulkdx.findprofessional.core.config.isDebug
import com.hulkdx.findprofessional.feature.developer.developerModule
import org.koin.core.module.Module


fun debugModules() = provideDebugModule {
    add(developerModule)
}

private fun provideDebugModule(block: MutableList<Module>.() -> Unit) =
    if (isDebug()) {
        buildList(block)
    } else emptyList()
