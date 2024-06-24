package com.hulkdx.findprofessional.common.feature.book.time

import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability
import com.hulkdx.findprofessional.common.utils.now
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlin.test.Test
import kotlin.test.assertEquals

class BookingTimeUtilsTest {

    private val sut = BookingTimeUtils

    @Test
    fun `isAvailabilityIncludedInTimes tests`() {
        data class TestData(
            val availabilityFrom: Int,
            val availabilityTo: Int,
            val from: Int,
            val to: Int,
            val exceptedResult: Boolean,
        )

        val testData = listOf(
            TestData(
                availabilityFrom = 0,
                availabilityTo = 30,
                from = 0,
                to = 30,
                exceptedResult = true,
            ),
            TestData(
                availabilityFrom = 0,
                availabilityTo = 0,
                from = 0,
                to = 30,
                exceptedResult = true,
            ),
            TestData(
                availabilityFrom = 0,
                availabilityTo = 30,
                from = 30,
                to = 60,
                exceptedResult = false,
            ),
            TestData(
                availabilityFrom = 0,
                availabilityTo = 90,
                from = 60,
                to = 90,
                exceptedResult = true,
            ),
            TestData(
                availabilityFrom = 23 * 60 + 30,
                availabilityTo = 0,
                from = 23 * 60 + 30,
                to = 24 * 60,
                exceptedResult = true,
            ),
        )

        for (t in testData) {
            // Arrange
            val availability = ProfessionalAvailability(
                date = LocalDate.now(), // irrelevant
                from = LocalTime.fromSecondOfDay(t.availabilityFrom * 60),
                to = LocalTime.fromSecondOfDay(t.availabilityTo * 60),
            )
            // Act
            val result = sut.isAvailabilityIncludedInTimes(availability, t.from, t.to)
            // Assert
            assertEquals(t.exceptedResult, result)
        }
    }

    @Test
    fun `currentDay tests`() {
        // Arrange
        val now = LocalDate(2024, 1, 1)
        // Act
        val result = sut.currentDay(now)
        // Assert
        assertEquals("1.1.2024", result)
    }
}
