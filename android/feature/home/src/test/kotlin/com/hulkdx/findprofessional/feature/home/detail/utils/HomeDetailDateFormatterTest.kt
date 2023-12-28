package com.hulkdx.findprofessional.feature.home.detail.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate


class HomeDetailDateFormatterTest {

    private val sut = HomeDetailDateFormatter

    @Test
    fun currentMonthTest() {
        // Arrange
        val now = LocalDate.of(2022, 1, 1)
        // Act
        val result = sut.currentMonth(now)
        // Assert
        assertEquals("January 2022", result)
    }

    @Test
    fun firstDayTest() {
        // Arrange
        val now = LocalDate.of(2023, 12, 21)
        // Act
        val result = sut.firstDay(now)
        // Assert
        assertEquals("Fri", result)
    }

    @Test
    fun firstDayIntTest() {
        // Arrange
        val now = LocalDate.of(2023, 12, 21)
        val expected = 4
        // Act
        val result = sut.firstDayInt(now)
        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun lengthOfMonthTest() {
        // Arrange
        val now = LocalDate.of(2023, 12, 21)
        val expected = 31
        // Act
        val result = sut.lengthOfMonth(now)
        // Assert
        assertEquals(expected, result)
    }

    // region helper methods -----------------------------------------------------------------------
    // endregion helper methods --------------------------------------------------------------------

}
