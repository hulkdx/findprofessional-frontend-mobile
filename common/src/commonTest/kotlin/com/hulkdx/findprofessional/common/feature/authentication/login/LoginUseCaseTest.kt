package com.hulkdx.findprofessional.common.feature.authentication.login

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.config.storage.RefreshTokenStorage
import com.hulkdx.findprofessional.common.config.storage.UserStorage
import com.hulkdx.findprofessional.common.feature.authentication.model.Auth
import com.hulkdx.findprofessional.common.feature.authentication.model.Token
import com.hulkdx.findprofessional.common.feature.authentication.model.User
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.LoginRequest
import com.hulkdx.findprofessional.common.utils.KoinTestUtil
import com.hulkdx.findprofessional.common.utils.StubNavigator
import com.hulkdx.findprofessional.common.utils.newUser
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class LoginUseCaseTest {

    private lateinit var sut: LoginUseCase

    private val loginApi = LoginApiMock()
    private val navigator = StubNavigator()
    private val accessTokenStorage = AccessTokenStorageMock()
    private val refreshTokenStorage = RefreshTokenStorageMock()

    @BeforeTest
    fun setUp() {
        KoinTestUtil.startKoin()
        sut = LoginUseCase(
            navigator,
            loginApi,
            accessTokenStorage,
            refreshTokenStorage,
            UserStorageFake
        )
    }

    @AfterTest
    fun tearDown() {
        KoinTestUtil.stopKoin()
    }

    @Test
    fun `onSignInClicked should store tokens`() = runTest {
        // Arrange
        val accessToken = "accessToken"
        val refreshToken = "accessToken"
        loginApi.loginReturns = Auth(Token(accessToken, refreshToken), newUser())
        // Act
        sut.onSignInClicked(LoginRequest("", ""))
        // Assert
        assertEquals(accessToken, accessTokenStorage.setValue)
        assertEquals(refreshToken, refreshTokenStorage.setValue)
    }

    // region mock classes

    private class LoginApiMock : LoginApi {
        lateinit var loginReturns: Auth

        override suspend fun login(request: LoginRequest): Auth {
            return loginReturns
        }
    }

    private class AccessTokenStorageMock : AccessTokenStorage {
        var setValue: String = ""

        override suspend fun get(): String? {
            return null
        }

        override suspend fun set(value: String) {
            setValue = value
        }
        override suspend fun remove() {}
    }

    private class RefreshTokenStorageMock : RefreshTokenStorage {
        var setValue: String = ""

        override suspend fun get(): String? {
            return null
        }

        override suspend fun set(value: String) {
            setValue = value
        }
        override suspend fun remove() {}
    }

    object UserStorageFake: UserStorage {
        override suspend fun get(): User? = null
        override suspend fun set(value: User) {}
        override suspend fun remove() {}
    }

    // endregion
}
