@file:Suppress("PrivatePropertyName")

package com.hulkdx.findprofessional.common.feature.home.detail.availability

import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability
import com.hulkdx.findprofessional.common.utils.createProfessional
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
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
    fun currentMonthTest() {
        // Arrange
        val now = LocalDate(2022, 1, 1)
        // Act
        val result = sut.currentMonth(now)
        // Assert
        assertEquals("January 2022", result)
    }

    @Test
    fun firstDayTest() {
        // Arrange
        val now = LocalDate(2023, 12, 21)
        // Act
        val result = sut.firstDay(now)
        // Assert
        assertEquals("Fri", result)
    }

    @Test
    fun firstDayIntTest() {
        // Arrange
        val now = LocalDate(2023, 12, 21)
        val expected = 4
        // Act
        val result = sut.firstDayInt(now)
        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun lengthOfMonthTest() {
        // Arrange
        val now = LocalDate(2023, 12, 21)
        val expected = 31
        // Act
        val result = sut.lengthOfMonth(now)
        // Assert
        assertEquals(expected, result)
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
            val pro = MutableStateFlow(createProfessional(t.availability))
            // Act
            val result = sut.getAvailabilityData(pro).first()
            // Assert
            assertEquals(t.expectedResult, result.professionalAvailabilityDates)
        }
    }

    private fun professionalAvailability(date: LocalDate) = ProfessionalAvailability(
        date,
        LocalTime.fromMillisecondOfDay(1),
        LocalTime.fromMillisecondOfDay(1),
    )
}
