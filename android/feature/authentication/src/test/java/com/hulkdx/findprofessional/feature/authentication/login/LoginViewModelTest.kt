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

    private lateinit var sut: LoginViewModel

    @Mock lateinit var loginRepository: LoginRepository
    @Mock lateinit var navigator: Navigator

    @BeforeEach
    fun setUp() {
        sut = LoginViewModel(
            loginRepository,
            navigator,
        )
    }

    @Test
    fun `onSignUpClicked should navigate to signup`() {
        // Arrange
        // Act
        sut.onSignUpClicked()
        // Assert
        verify(navigator).navigate(SignUpNavigationScreen())
    }
}
