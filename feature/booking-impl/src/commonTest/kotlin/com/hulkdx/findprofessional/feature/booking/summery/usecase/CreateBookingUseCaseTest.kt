package com.hulkdx.findprofessional.feature.booking.summery.usecase

import com.hulkdx.findprofessional.feature.authentication.model.user.UserData
import com.hulkdx.findprofessional.libs.common.tests.StubProfessionalApi
import com.hulkdx.findprofessional.libs.common.tests.StubUserStorage
import kotlin.test.BeforeTest

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
}
