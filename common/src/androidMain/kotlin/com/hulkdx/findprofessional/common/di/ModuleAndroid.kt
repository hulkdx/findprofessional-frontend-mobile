package com.hulkdx.findprofessional.common.di

import com.hulkdx.findprofessional.common.config.PlatformSpecific
import com.hulkdx.findprofessional.common.config.PlatformSpecificAndroid
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


actual fun platformModule() = module {
    singleOf<PlatformSpecific>(::PlatformSpecificAndroid)
}


