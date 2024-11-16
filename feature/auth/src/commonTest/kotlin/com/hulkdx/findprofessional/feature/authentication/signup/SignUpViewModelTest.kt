@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hulkdx.findprofessional.feature.authentication.signup

import com.hulkdx.findprofessional.core.model.user.Token
import com.hulkdx.findprofessional.core.model.user.UserData
import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.libs.common.tests.StubNavigator
import com.hulkdx.findprofessional.libs.common.tests.newUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SignUpViewModelTest {

    private lateinit var sut: SignUpViewModel

    private val api = ApiMock()
    private val navigator = StubNavigator()
    private val userStorage = UserStorageMock()

    @BeforeTest
    fun setUp() {
        sut = SignUpViewModel(
            navigator,
            SignUpUseCase(
                api,
                userStorage,
            ),
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onSubmitClicked should store tokens`() = runTest {
        // Arrange
        val accessToken = "accessToken"
        val refreshToken = "refreshToken"
        api.registerReturns = UserData(Token(accessToken, refreshToken), newUser())
        // Act
        sut.onSubmitClicked()
        // Assert
        assertEquals(accessToken, userStorage.setValue?.token?.accessToken)
        assertEquals(refreshToken, userStorage.setValue?.token?.refreshToken)
    }

    // region mock classes

    private class ApiMock : SignUpApi {
        lateinit var registerReturns: UserData

        override suspend fun register(request: RegisterRequest): UserData {
            return registerReturns
        }
    }

    private class UserStorageMock : UserStorage {
        var setValue: UserData? = null

        override suspend fun get() = null
        override suspend fun set(value: UserData) {
            setValue = value
        }

        override suspend fun remove() {}
        override fun getFlow(): Flow<UserData?> = flow {}
    }

    // endregion
}
