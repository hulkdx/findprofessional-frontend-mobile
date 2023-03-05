package com.hulkdx.findprofessional.common.feature.authentication.signup

import com.hulkdx.findprofessional.common.config.storage.AccessTokenStorage
import com.hulkdx.findprofessional.common.config.storage.RefreshTokenStorage
import com.hulkdx.findprofessional.common.feature.authentication.login.AuthToken
import com.hulkdx.findprofessional.common.feature.authentication.login.LoginUseCase
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import com.hulkdx.findprofessional.common.utils.KoinTestUtil
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SignUpUseCaseTest {

    private lateinit var sut: SignUpUseCase

    private val loginApi = SignUpApiMock()
    private val navigator = NavigatorMock()
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
        loginApi.registerReturns = AuthToken(accessToken, refreshToken)
        // Act
        sut.onSubmitClicked(AuthRequest("", ""))
        // Assert
        assertEquals(accessToken, accessTokenStorage.setValue)
        assertEquals(refreshToken, refreshTokenStorage.setValue)
    }

    // region mock classes

    private class SignUpApiMock : SignUpApi {
        lateinit var registerReturns: AuthToken

        override suspend fun register(request: AuthRequest): AuthToken {
            return registerReturns
        }
    }

    private class NavigatorMock : Navigator {
        override fun navigate(screen: NavigationScreen) {
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
    }

    private class RefreshTokenStorageMock : RefreshTokenStorage {
        var setValue: String = ""

        override suspend fun get(): String? {
            return null
        }

        override suspend fun set(value: String) {
            setValue = value
        }
    }

    // endregion
}
