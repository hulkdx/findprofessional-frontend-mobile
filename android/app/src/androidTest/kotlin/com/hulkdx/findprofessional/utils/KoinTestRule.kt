package com.hulkdx.findprofessional.utils

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.hulkdx.findprofessional.common.navigation.Navigator
import com.hulkdx.findprofessional.navigation.NavigatorImpl
import okio.Path.Companion.toOkioPath
import org.junit.rules.ExternalResource
import org.junit.rules.TemporaryFolder
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

class KoinTestRule(
    private vararg val additional: Module
) : ExternalResource() {

    private val temporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    private val testModules = module {
        singleOf(::NavigatorImpl) bind Navigator::class

        single {
            PreferenceDataStoreFactory.createWithPath(produceFile = {
                temporaryFolder.newFile("user_preferences_test.preferences_pb").toOkioPath()
            })
        }
    }

    override fun before() {
        temporaryFolder.create()
        loadKoinModules(testModules)
        loadKoinModules(additional.toList())
    }

    override fun after() {
        temporaryFolder.delete()
        unloadKoinModules(testModules)
        unloadKoinModules(additional.toList())
    }
}
