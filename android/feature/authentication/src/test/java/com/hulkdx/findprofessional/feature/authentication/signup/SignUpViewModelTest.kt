package com.hulkdx.findprofessional.feature.authentication.signup

import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpRepository
import com.hulkdx.findprofessional.feature.authentication.login.LoginViewModel
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class SignUpViewModelTest {
    private lateinit var sut: SignUpViewModel

    @BeforeEach
    fun setUp() {
        sut = SignUpViewModel(
            SignUpRepository()
        )
    }
}
