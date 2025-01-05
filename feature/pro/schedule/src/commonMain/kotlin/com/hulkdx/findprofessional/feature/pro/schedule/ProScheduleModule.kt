package com.hulkdx.findprofessional.feature.pro.schedule

import com.hulkdx.findprofessional.core.features.pro.storage.AvailabilityStorage
import com.hulkdx.findprofessional.core.features.pro.storage.AvailabilityStorageDataStore
import com.hulkdx.findprofessional.core.features.pro.usecase.GetAvailabilityUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val proScheduleModule: Module
    get() = module {
        viewModelOf(::ProScheduleViewModel)

        singleOf(::GetAvailabilityUseCase)
        factoryOf(::AvailabilityStorageDataStore) bind AvailabilityStorage::class
    }
