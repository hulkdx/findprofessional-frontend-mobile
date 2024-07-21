package com.hulkdx.findprofessional.libs.navigation.decompose

import com.arkivanov.decompose.router.stack.StackNavigation
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val decomposeModule: Module
    get() = module {
        single { StackNavigation<NavigationScreen>() }
        singleOf(::DecomposeNavigator) bind Navigator::class
    }
