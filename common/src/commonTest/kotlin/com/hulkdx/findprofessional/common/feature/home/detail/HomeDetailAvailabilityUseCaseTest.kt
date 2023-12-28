@file:Suppress("PrivatePropertyName")

package com.hulkdx.findprofessional.common.feature.home.detail

import kotlinx.datetime.LocalDate
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeDetailAvailabilityUseCaseTest {

    private lateinit var sut: HomeDetailAvailabilityUseCase

    @BeforeTest
    fun setUp() {
        sut = HomeDetailAvailabilityUseCase()
    }

    @Test
    fun currentMonthTest() {
        // Arrange
        val now = LocalDate(2022, 1, 1)
        // Act
        val result = sut.currentMonth(now)
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
}
