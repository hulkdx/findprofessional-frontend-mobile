package com.hulkdx.findprofessional.feature.book.summery.usecase

import com.hulkdx.findprofessional.libs.common.tests.StubProfessionalApi
import com.hulkdx.findprofessional.libs.common.tests.createProfessional
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class CreateBookingUseCaseTest {
    private lateinit var sut: CreateBookingUseCase

    private val api = StubProfessionalApi()

    @BeforeTest
    fun setUp() {
        sut = CreateBookingUseCase(api)
    }

    @Test
    fun `should not call api if user has no skype-id`() = runBlocking {
        // Arrange
        // Act
        sut.execute(
            amountInCents = 5,
            currency = "EUR",
            availabilities = listOf(),
            professional = createProfessional()
        )
        // Assert
    }
}
