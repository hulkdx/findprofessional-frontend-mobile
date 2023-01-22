package com.hulkdx.findprofessional.common.di

import com.hulkdx.findprofessional.common.feature.authentication.login.LoginUseCase
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


// Used by iOS app to inject koin
@Suppress("unused")
class KoinHelper : KoinComponent {
    val loginUseCase: LoginUseCase by inject()
    val signUpUseCase: SignUpUseCase by inject()
}
