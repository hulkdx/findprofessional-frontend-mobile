package com.hulkdx.findprofessional.feature.profile

import com.hulkdx.findprofessional.feature.profile.edit.ProfileEditViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val profileModule: Module
    get() = module {
        viewModelOf(::ProfileViewModel)
        viewModelOf(::ProfileEditViewModel)
    }
