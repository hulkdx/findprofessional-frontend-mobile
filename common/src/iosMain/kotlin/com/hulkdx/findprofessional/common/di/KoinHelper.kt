package com.hulkdx.findprofessional.common.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.hulkdx.findprofessional.common.feature.authentication.login.LoginUseCase
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpUseCase
import com.hulkdx.findprofessional.common.navigation.Navigator
import kotlinx.coroutines.runBlocking
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.definition.Definition
import org.koin.dsl.module

// Used by iOS app to inject koin
@Suppress("unused")
class KoinHelper : KoinComponent {
    val loginUseCase: LoginUseCase by inject()
    val signUpUseCase: SignUpUseCase by inject()
    val navigator: Navigator by inject()

    fun clearDatastore() {
        val dataStore: DataStore<Preferences> by inject()
        runBlocking { dataStore.edit { it.clear() } }
    }
}

