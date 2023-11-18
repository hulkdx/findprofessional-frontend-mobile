package com.hulkdx.findprofessional.utils

import android.os.Environment
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onRoot
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
    private val additionalRules: () -> Unit = {}
) : TestRule, KoinComponent {

    private val navigator: NavigatorImpl by inject()
    private val dataStore: DataStore<Preferences> by inject()

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                setup()
                base.evaluate()
            }
        }
    }

    private fun setup() {
        navigator.screenState.value = null
        runBlocking { dataStore.edit { it.clear() } }
        additionalRules()
    }
}
