@file:Suppress("PrivatePropertyName")

package com.hulkdx.findprofessional.core.utils

import kotlinx.datetime.LocalDate
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DateUtilsTest {

    private val sut = DateUtils

    @BeforeTest
    fun setUp() {
    }

    @Test
    fun formatToMonthsAndYearTest() {
        // Arrange
        val now = LocalDate(2022, 1, 1)
        // Act
        val result = sut.formatToMonthsAndYear(now)
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
}
