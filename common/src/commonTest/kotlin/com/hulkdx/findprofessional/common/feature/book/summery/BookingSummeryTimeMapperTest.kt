package com.hulkdx.findprofessional.common.feature.book.summery

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

}
