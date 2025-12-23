package com.hulkdx.findprofessional.feature.profile

import com.hulkdx.findprofessional.feature.profile.edit.ProfileEditViewModel
import com.hulkdx.findprofessional.feature.profile.edit.api.UpdateProfileApi
import com.hulkdx.findprofessional.feature.profile.edit.api.UpdateProfileApiImpl
import com.hulkdx.findprofessional.feature.profile.edit.usecase.UpdateProfileUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val profileModule: Module
    get() = module {
        viewModelOf(::ProfileViewModel)
        viewModelOf(::ProfileEditViewModel)
        factoryOf(::UpdateProfileUseCase)
        factoryOf(::UpdateProfileApiImpl) bind UpdateProfileApi::class
    }
