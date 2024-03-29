package com.hulkdx.findprofessional.common.feature.authentication.signup

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.config.storage.RefreshTokenStorage
import com.hulkdx.findprofessional.common.feature.authentication.login.LoginUseCaseTest.UserStorageFake
import com.hulkdx.findprofessional.common.feature.authentication.model.Auth
import com.hulkdx.findprofessional.common.feature.authentication.model.Token
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.common.utils.KoinTestUtil
import com.hulkdx.findprofessional.common.utils.StubNavigator
import com.hulkdx.findprofessional.common.utils.newUser
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SignUpUseCaseTest {

    private lateinit var sut: SignUpUseCase

    private val loginApi = SignUpApiMock()
    private val navigator = StubNavigator()
    private val accessTokenStorage = AccessTokenStorageMock()
    private val refreshTokenStorage = RefreshTokenStorageMock()

    @BeforeTest
    fun setUp() {
        KoinTestUtil.startKoin()
        sut = SignUpUseCase(
            navigator,
            loginApi,
            accessTokenStorage,
            refreshTokenStorage,
            UserStorageFake,
        )
    }

    @AfterTest
    fun tearDown() {
        KoinTestUtil.stopKoin()
    }

    @Test
    fun `onSubmitClicked should store tokens`() = runTest {
        // Arrange
        val accessToken = "accessToken"
        val refreshToken = "accessToken"
        loginApi.registerReturns = Auth(Token(accessToken, refreshToken), newUser())
        // Act
        sut.onSubmitClicked(
            RegisterRequest(
                "", "",
                firstName = "",
                lastName = "",
            )
        )
        // Assert
        assertEquals(accessToken, accessTokenStorage.setValue)
        assertEquals(refreshToken, refreshTokenStorage.setValue)
    }

    // region mock classes

    private class SignUpApiMock : SignUpApi {
        lateinit var registerReturns: Auth

        override suspend fun register(request: RegisterRequest): Auth {
            return registerReturns
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

    // endregion
}
