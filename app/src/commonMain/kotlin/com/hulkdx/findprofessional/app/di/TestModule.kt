package com.hulkdx.findprofessional.app.di

import com.hulkdx.findprofessional.common.config.PlatformSpecific
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
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

        factory {
            object : PlatformSpecific {
                override fun isDebug(): Boolean {
                    return true
                }

                override fun localhostUrl(): String {
                    return "10.0.2.2"
                }

                override fun appDirectoryPath(): String {
                    return ""
                }
            }
        } bind PlatformSpecific::class
    }
