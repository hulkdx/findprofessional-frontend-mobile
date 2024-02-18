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
        val startTimes = setOf(0)
        val item = date to startTimes
        val expectedResult = BookingSummeryUiState.Time(
            duration = "00:00 - 00:30",
            date = "JAN 10, 2024",
            day = "MON",
        )
        // Act
        val result = sut.map(item)
        // Assert
        assertEquals(result, expectedResult)
    }

}
