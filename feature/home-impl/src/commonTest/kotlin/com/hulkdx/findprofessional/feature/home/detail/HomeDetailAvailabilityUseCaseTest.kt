@file:Suppress("PrivatePropertyName")

package com.hulkdx.findprofessional.feature.home.detail

import com.hulkdx.findprofessional.feature.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.feature.home.detail.availability.HomeDetailAvailabilityUseCase
import com.hulkdx.findprofessional.libs.common.tests.createProfessional
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeDetailAvailabilityUseCaseTest {

    private lateinit var sut: HomeDetailAvailabilityUseCase

    @BeforeTest
    fun setUp() {
        sut = HomeDetailAvailabilityUseCase()
    }

    @Test
    fun getAvailabilityDataTests() = runTest {
        data class TestData(
            val availability: List<ProfessionalAvailability>,
            val expectedResult: List<LocalDate>,
        )

        val testData = listOf(
            TestData(
                availability = listOf(),
                expectedResult = listOf()
            ),
            TestData(
                availability = listOf(
                    professionalAvailability(LocalDate(2023, 1, 1))
                ),
                expectedResult = listOf(LocalDate(2023, 1, 1))
            ),
        )

        for (t in testData) {
            // Arrange
            val pro = createProfessional(t.availability)
            // Act
            val result = sut.getAvailabilityData(pro).first()
            // Assert
            assertEquals(t.expectedResult, result.professionalAvailabilityDates)
        }
    }

    private fun professionalAvailability(date: LocalDate) = ProfessionalAvailability(
        id = 1,
        from = date.atTime(0, 0).toInstant(TimeZone.UTC),
        to = date.atTime(0, 0).toInstant(TimeZone.UTC),
    )
}
