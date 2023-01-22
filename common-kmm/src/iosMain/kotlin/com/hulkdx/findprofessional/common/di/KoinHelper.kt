package com.hulkdx.findprofessional.common.di

import com.hulkdx.findprofessional.common.feature.authentication.login.LoginRepository
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


// Used by iOS app
@Suppress("unused")
class KoinHelper : KoinComponent {
    val loginRepository: LoginRepository by inject()
    val signUpRepository: SignUpRepository by inject()
}
