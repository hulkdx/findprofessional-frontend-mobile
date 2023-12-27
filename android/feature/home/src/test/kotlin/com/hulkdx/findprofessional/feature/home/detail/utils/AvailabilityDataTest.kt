package com.hulkdx.findprofessional.feature.home.detail.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate


class AvailabilityDataTest {

    @Test
    fun `when isSelectedDay is included selectedItemForThisMonth then it should return true`() {
        // Arrange
        val now = LocalDate.of(2023, 12, 27)
        val selectedItemForThisMonth = listOf(
            LocalDate.of(2023, 12, 27)
        )

        val availabilityData = createAvailabilityData(
            now,
            selectedItemForThisMonth,
        )
        // Act
        val result = availabilityData.isSelectedDay(27)
        // Assert
        assertEquals(true, result)
    }

    @Test
    fun `when isSelectedDay is not included selectedItemForThisMonth then it should return true`() {
        // Arrange
        val now = LocalDate.of(2023, 12, 27)
        val selectedItemForThisMonth = listOf(
            LocalDate.of(2023, 12, 27)
        )

        val availabilityData = createAvailabilityData(
            now,
            selectedItemForThisMonth,
        )
        // Act
        val result = availabilityData.isSelectedDay(28)
        // Assert
        assertEquals(false, result)
    }

    private fun createAvailabilityData(
        now: LocalDate,
        selectedItemForThisMonth: List<LocalDate>,
    ) =
        AvailabilityData(
            calendarDateFormat = "",
            firstDay = 0,
            lengthOfMonth = 0,
            now = now.toEpochDay(),
            selectedItemForThisMonth = selectedItemForThisMonth
        )

}
