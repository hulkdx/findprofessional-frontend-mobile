package com.hulkdx.findprofessional.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.hulkdx.findprofessional.navigation.NavigatorImpl
import kotlinx.coroutines.runBlocking
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BeforeComposeRule(
    private val additionalSetup: () -> Unit = {}
) : TestRule, KoinComponent {

    private val navigator: NavigatorImpl by inject()
    private val dataStore: DataStore<Preferences> by inject()

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                try {
                    setup()
                    base.evaluate()
                } finally {
                    tearDown()
                }
            }
        }
    }

    private fun setup() {
        additionalSetup()
    }

    private fun tearDown() {
        navigator.screenState.value = null
        runBlocking { dataStore.edit { it.clear() } }
    }
}
