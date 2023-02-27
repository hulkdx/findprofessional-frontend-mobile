package com.hulkdx.findprofessional.common.feature.authentication.login

import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class LoginViewModelTest {

    private lateinit var sut: LoginUseCase

    private val loginApi = LoginApiMock()
    private val navigator = NavigatorMock()

    @BeforeTest
    fun setUp() {
        sut = LoginUseCase(
            navigator,
            loginApi,
        )
    }

    @Test
    fun `SampleFailingTest`() {
        assertEquals(true, true)
    }

    private class LoginApiMock: LoginApi {
        override suspend fun login(request: AuthRequest): LoginResponse {
            TODO("Not yet implemented")
        }
    }

    private class NavigatorMock: Navigator {
        override fun navigate(screen: NavigationScreen) {
            TODO("Not yet implemented")
        }
    }
}
