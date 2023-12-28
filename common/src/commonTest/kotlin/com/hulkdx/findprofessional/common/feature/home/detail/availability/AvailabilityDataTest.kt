package com.hulkdx.findprofessional.common.feature.home.detail.availability

import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class AvailabilityDataTest {

    @Test
    fun `when isSelectedDay is included selectedItemForThisMonth then it should return true`() {
        // Arrange
        val now = LocalDate(2023, 12, 27)
        val selectedItemForThisMonth = listOf(
            LocalDate(2023, 12, 27)
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
        val now = LocalDate(2023, 12, 27)
        val selectedItemForThisMonth = listOf(
            LocalDate(2023, 12, 27)
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
            currentMonth = "",
            firstDay = 0,
            lengthOfMonth = 0,
            now = now.toEpochDays(),
            professionalAvailabilityDates = selectedItemForThisMonth
        )
}
