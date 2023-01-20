package com.hulkdx.findprofessional.di

import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.navigation.NavigatorImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule: Module
    get() = module {
        singleOf(::NavigatorImpl) bind Navigator::class
    }
