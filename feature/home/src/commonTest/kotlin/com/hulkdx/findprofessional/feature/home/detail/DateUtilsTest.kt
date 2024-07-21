@file:Suppress("PrivatePropertyName")

package com.hulkdx.findprofessional.feature.home.detail

import com.hulkdx.findprofessional.feature.home.detail.utils.DateUtils
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
    fun currentMonthTest() {
        // Arrange
        val now = LocalDate(2022, 1, 1)
        // Act
        val result = DateUtils.currentMonth(now)
        // Assert
        assertEquals("January 2022", result)
    }

    @Test
    fun firstDayTest() {
        // Arrange
        val now = LocalDate(2023, 12, 21)
        // Act
        val result = DateUtils.firstDay(now)
        // Assert
        assertEquals("Fri", result)
    }

    @Test
    fun firstDayIntTest() {
        // Arrange
        val now = LocalDate(2023, 12, 21)
        val expected = 4
        // Act
        val result = DateUtils.firstDayInt(now)
        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun lengthOfMonthTest() {
        // Arrange
        val now = LocalDate(2023, 12, 21)
        val expected = 31
        // Act
        val result = DateUtils.lengthOfMonth(now)
        // Assert
        assertEquals(expected, result)
    }
}
