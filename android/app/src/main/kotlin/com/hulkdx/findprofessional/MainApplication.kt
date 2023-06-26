package com.hulkdx.findprofessional

import android.app.Application
import com.hulkdx.findprofessional.common.BuildConfig
import com.hulkdx.findprofessional.common.config.api.InMemoryApi
import com.hulkdx.findprofessional.common.config.storage.isMockData
import com.hulkdx.findprofessional.common.di.initKoin
import com.hulkdx.findprofessional.di.appModule
import com.hulkdx.findprofessional.feature.authentication.login.loginModule
import com.hulkdx.findprofessional.feature.authentication.signup.signUpModule
import com.hulkdx.findprofessional.feature.authentication.splash.splashModule
import com.hulkdx.findprofessional.feature.developer.developerModule
import com.hulkdx.findprofessional.feature.home.homeModule
import com.hulkdx.findprofessional.feature.profile.profileModule
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@MainApplication)
            modules(
                appModule,
                loginModule,
                signUpModule,
                homeModule,
                developerModule,
                splashModule,
                profileModule,
            )
        }

        if (BuildConfig.DEBUG) {
            runBlocking {
                if (isMockData()) {
                    loadKoinModules(InMemoryApi.module)
                }
            }
        }
    }
}
