package com.hulkdx.findprofessional.utils

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.hulkdx.findprofessional.di.appModule
import com.hulkdx.findprofessional.feature.authentication.login.loginModule
import com.hulkdx.findprofessional.feature.authentication.signup.signUpModule
import com.hulkdx.findprofessional.feature.authentication.splash.splashModule
import com.hulkdx.findprofessional.feature.developer.developerModule
import com.hulkdx.findprofessional.feature.home.detail.homeDetailModule
import com.hulkdx.findprofessional.feature.home.homeModule
import com.hulkdx.findprofessional.feature.profile.profileModule
import okio.Path.Companion.toOkioPath
import org.junit.rules.ExternalResource
import org.junit.rules.TemporaryFolder
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

class KoinTestRule(
    private vararg val additional: Module,
) : ExternalResource() {

    private val temporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    private val testModules = module {
        single {
            PreferenceDataStoreFactory.createWithPath(produceFile = {
                temporaryFolder.newFile("user_preferences_test.preferences_pb").toOkioPath()
            })
        }
    }

    override fun apply(base: Statement, description: Description): Statement {
        return super.apply(base, description)
    }

    override fun before() {
        temporaryFolder.create()
        loadKoinModules(
            listOf(
                appModule,
                loginModule,
                signUpModule,
                homeModule,
                homeDetailModule,
                developerModule,
                splashModule,
                profileModule,
            )
        )
        loadKoinModules(testModules)
        loadKoinModules(additional.toList())
    }

    override fun after() {
        temporaryFolder.delete()
        unloadKoinModules(testModules)
        unloadKoinModules(additional.toList())
    }
}
