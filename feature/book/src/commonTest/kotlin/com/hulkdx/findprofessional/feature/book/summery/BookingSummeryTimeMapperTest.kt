package com.hulkdx.findprofessional.feature.book.summery

import com.hulkdx.findprofessional.core.features.book.BookingSummeryTime
import com.hulkdx.findprofessional.core.features.book.SelectedTimes
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
        val expectedResult = BookingSummeryTime(
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
            BookingSummeryTime(
                duration = "00:00 - 00:30",
                date = "1.1.2024",
                day = "Mon",
            ),
            BookingSummeryTime(
                duration = "00:30 - 01:00",
                date = "1.1.2024",
                day = "Mon",
            ),
            BookingSummeryTime(
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

    @Test
    fun `formattedTotalPrices tests`() {
        data class TestData(
            val currency: String,
            val amountInCents: Long,
            val expectedResult: String,
        )

        val testData = listOf(
            TestData(currency = "USD", amountInCents = 100, expectedResult = "1.00 $"),
            TestData(currency = "EUR", amountInCents = 180, expectedResult = "1.80 €"),
            TestData(currency = "USD", amountInCents = 0, expectedResult = "0.00 $"),
            TestData(currency = "EUR", amountInCents = 5, expectedResult = "0.05 €"),
            TestData(currency = "USD", amountInCents = 999, expectedResult = "9.99 $"),
            TestData(currency = "EUR", amountInCents = 250, expectedResult = "2.50 €"),
            TestData(currency = "EUR", amountInCents = 1, expectedResult = "0.01 €"),
            TestData(currency = "USD", amountInCents = 100000, expectedResult = "1000.00 $"),
            TestData(currency = "EUR", amountInCents = 123456, expectedResult = "1234.56 €"),
        )
        for (t in testData) {
            // Arrange
            // Act
            val result = sut.formattedTotalPrices(
                t.amountInCents,
                t.currency,
            )
            // Assert
            assertEquals(t.expectedResult, result)
        }
    }
}
