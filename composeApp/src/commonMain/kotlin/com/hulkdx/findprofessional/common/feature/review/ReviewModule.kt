package com.hulkdx.findprofessional.common.feature.review

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val reviewModule: Module
    get() = module {
        factoryOf(::ReviewUseCase)
    }
