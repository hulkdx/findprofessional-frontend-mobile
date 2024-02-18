package com.hulkdx.findprofessional.common.feature.book.summery

import com.hulkdx.findprofessional.common.feature.book.time.SelectedTimes
import kotlinx.datetime.LocalDate
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class BookingSummeryTimeMapperTest {

    private lateinit var sut: BookingSummeryTimeMapper

    @BeforeTest
    fun setUp() {
        sut = BookingSummeryTimeMapper()
    }

    @Test
    fun `map times tests`() {
        // Arrange
        val date = LocalDate(2024, 1, 1)
        val startTime = 0
        val expectedResult = BookingSummeryUiState.Time(
            duration = "00:00 - 00:30",
            date = "1.1.2024",
            day = "Mon",
        )
        // Act
        val result = sut.map(date, startTime)
        // Assert
        assertEquals(result, expectedResult)
    }

    @Test
    fun `map selectedTimes tests`() {
        // Arrange
        val selectedTimes = SelectedTimes(
            mapOf(
                LocalDate(2024, 1, 1) to setOf(0, 30),
                LocalDate(2024, 1, 2) to setOf(60),
            )
        )
        val expectedResult = listOf(
            BookingSummeryUiState.Time(
                duration = "00:00 - 00:30",
                date = "1.1.2024",
                day = "Mon",
            ),
            BookingSummeryUiState.Time(
                duration = "00:30 - 01:00",
                date = "1.1.2024",
                day = "Mon",
            ),
            BookingSummeryUiState.Time(
                duration = "01:00 - 01:30",
                date = "2.1.2024",
                day = "Tue",
            ),
        )
        // Act
        val result = sut.map(selectedTimes)
        // Assert
        assertEquals(result, expectedResult)
    }
}
