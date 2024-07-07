package com.hulkdx.findprofessional.app.di

import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

// TODO: fix
val testModule: Module
    get() = module {
        factory {
            object : Navigator {
                override fun navigate(screen: NavigationScreen) {
                }

                override fun navigate(
                    screen: NavigationScreen,
                    popTo: NavigationScreen,
                    inclusive: Boolean,
                ) {
                }

                override fun goBack() {
                }

                override fun getCurrentScreen(): NavigationScreen {
                    TODO()
                }
            }
        } bind Navigator::class
    }
