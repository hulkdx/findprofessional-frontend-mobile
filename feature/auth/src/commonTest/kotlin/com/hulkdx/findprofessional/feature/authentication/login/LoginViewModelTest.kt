@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hulkdx.findprofessional.feature.authentication.login

import com.hulkdx.findprofessional.core.model.user.Token
import com.hulkdx.findprofessional.core.model.user.UserData
import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.feature.authentication.login.api.LoginApi
import com.hulkdx.findprofessional.feature.authentication.login.model.LoginRequest
import com.hulkdx.findprofessional.libs.common.tests.StubNavigator
import com.hulkdx.findprofessional.libs.common.tests.newUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class LoginViewModelTest {

    private lateinit var sut: LoginViewModel

    private val loginApi = LoginApiMock()
    private val navigator = StubNavigator()
    private val userStorage = UserStorageMock()

    @BeforeTest
    fun setUp() {
        sut = LoginViewModel(
            LoginUseCase(
                loginApi,
                userStorage,
            ),
            navigator,
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onSignInClicked should store tokens`() {
        // Arrange
        val accessToken = "accessToken"
        val refreshToken = "refreshToken"
        loginApi.loginReturns = UserData(Token(accessToken, refreshToken), newUser())
        // Act
        sut.onSignInClicked()
        // Assert
        assertEquals(accessToken, userStorage.setValue?.token?.accessToken)
        assertEquals(refreshToken, userStorage.setValue?.token?.refreshToken)
    }

    // region mock classes

    private class LoginApiMock : LoginApi {
        lateinit var loginReturns: UserData

        override suspend fun login(request: LoginRequest): UserData {
            return loginReturns
        }
    }

    private class UserStorageMock : UserStorage {
        var setValue: UserData? = null

        override suspend fun get() = null
        override suspend fun set(value: UserData) {
            setValue = value
        }

        override suspend fun remove() {}
    }

    // endregion
}
