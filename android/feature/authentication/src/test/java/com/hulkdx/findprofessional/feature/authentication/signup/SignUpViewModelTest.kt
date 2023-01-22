package com.hulkdx.findprofessional.feature.authentication.signup

import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpApi
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpUseCase
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class SignUpViewModelTest {
    private lateinit var sut: SignUpViewModel

    @Mock
    private lateinit var api: SignUpApi

    @BeforeEach
    fun setUp() {
        sut = SignUpViewModel(
            SignUpUseCase(api)
        )
    }
}
