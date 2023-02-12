package com.hulkdx.findprofessional.common.di

import com.hulkdx.findprofessional.common.config.PlatformSpecific
import com.hulkdx.findprofessional.common.config.PlatformSpecificIOS
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

actual fun platformModule() = module {
    factoryOf<PlatformSpecific>(::PlatformSpecificIOS)
}
