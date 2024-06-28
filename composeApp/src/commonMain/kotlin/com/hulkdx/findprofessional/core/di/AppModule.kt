package com.hulkdx.findprofessional.core.di

import com.hulkdx.findprofessional.core.platform.Context
import com.hulkdx.findprofessional.features.splash.splashModule

fun appModule(appContext: Context) = listOf(
    splashModule
)
