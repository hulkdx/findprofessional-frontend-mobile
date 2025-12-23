package com.hulkdx.findprofessional.feature.booking.summery.usecase

import com.hulkdx.findprofessional.core.features.user.Token
import com.hulkdx.findprofessional.core.features.user.User
import com.hulkdx.findprofessional.core.features.user.UserData
import com.hulkdx.findprofessional.feature.booking.summery.exception.SkypeIdNotFound
import com.hulkdx.findprofessional.libs.common.tests.StubProfessionalApi
import com.hulkdx.findprofessional.libs.common.tests.StubUserStorage
import com.hulkdx.findprofessional.libs.common.tests.createProfessional
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class CreateBookingUseCaseTest {
    private lateinit var sut: CreateBookingUseCase

    private val api = StubProfessionalApi()
    private val userStorage = object : StubUserStorage() {
        lateinit var userData: UserData

        override suspend fun get(): UserData? {
            return userData
        }
    }

    @BeforeTest
    fun setUp() {
        sut = CreateBookingUseCase(
            api,
            userStorage,
        )
    }

    @Test
    fun `should not call api if user has no skype-id`() {
        data class TestData(
            val skypeId: String?,
            val expectedError: Boolean,
        )

        val testData = listOf(
            TestData(
                skypeId = null,
                expectedError = true,
            ),
            TestData(
                skypeId = "",
                expectedError = true,
            ),
            TestData(
                skypeId = "   ",
                expectedError = true,
            ),
            TestData(
                skypeId = "something",
                expectedError = false,
            )
        )
        for (t in testData) {
            runBlocking {
                // Arrange
                userStorage.userData = UserData(
                    Token(accessToken = "laudem", refreshToken = "dapibus"),
                    user = User(skypeId = t.skypeId)
                )
                // Act
                val res = sut.execute(
                    amountInCents = 5,
                    currency = "EUR",
                    availabilities = listOf(),
                    professional = createProfessional()
                )
                // Assert
                assertTrue("skypeId='${t.skypeId}' expectedError=${t.expectedError}") {
                    if (t.expectedError) {
                        res.exceptionOrNull() is SkypeIdNotFound
                    } else {
                        res.exceptionOrNull() == null
                    }
                }
            }
        }
    }
}
