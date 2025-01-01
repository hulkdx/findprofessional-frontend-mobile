package com.hulkdx.findprofessional.app.di

import android.content.Context
import com.hulkdx.findprofessional.core.platform.PlatformSpecific
import com.hulkdx.findprofessional.core.platform.PlatformSpecificAndroid
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module


fun androidAppModule(appContext: Context) = module {
    factoryOf(::PlatformSpecificAndroid) bind PlatformSpecific::class
    single { appContext }
}
