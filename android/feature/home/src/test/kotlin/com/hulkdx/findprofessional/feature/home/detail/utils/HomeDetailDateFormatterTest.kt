package com.hulkdx.findprofessional.feature.home.detail.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate


class HomeDetailDateFormatterTest {

    private val sut = HomeDetailDateFormatter

    @Test
    fun calendarDateFormatTest() {
        // Arrange
        val now = LocalDate.of(2022, 1, 1)
        // Act
        val result = sut.calendarDateFormat(now)
        // Assert
        assertEquals("January 2022", result)
    }


    // region helper methods -----------------------------------------------------------------------
    // endregion helper methods --------------------------------------------------------------------

}
