package com.hulkdx.findprofessional.feature.authentication.login

import com.hulkdx.findprofessional.common.feature.authentication.login.LoginRepository
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpNavigationScreen
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel

    @Mock lateinit var loginRepository: LoginRepository
    @Mock lateinit var navigator: Navigator

    @BeforeEach
    fun setUp() {
        viewModel = LoginViewModel(
            loginRepository,
            navigator,
        )
    }

    @Test
    fun `onSignUpClicked should navigate to signup`() {
        viewModel.onSignUpClicked()

        verify(navigator).navigate(SignUpNavigationScreen())
    }
}
