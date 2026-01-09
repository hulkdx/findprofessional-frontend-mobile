package com.hulkdx.findprofessional.feature.pro.schedule

import com.hulkdx.findprofessional.feature.pro.schedule.usecase.GetScheduleUseCase
import com.hulkdx.findprofessional.feature.pro.storage.AvailabilityStorage
import com.hulkdx.findprofessional.feature.pro.storage.AvailabilityStorageDataStore
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val proScheduleModule: Module
    get() = module {
        viewModelOf(::ProScheduleViewModel)

        factoryOf(::AvailabilityStorageDataStore) bind AvailabilityStorage::class
        factoryOf(::GetScheduleUseCase)
    }
