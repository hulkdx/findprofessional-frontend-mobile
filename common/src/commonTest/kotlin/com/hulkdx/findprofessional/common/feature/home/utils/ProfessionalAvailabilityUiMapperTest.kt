package com.hulkdx.findprofessional.common.feature.home.utils

import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability
import com.hulkdx.findprofessional.common.utils.mockClock
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalTime
import kotlin.test.Test
import kotlin.test.assertEquals


class ProfessionalAvailabilityUiMapperTest {

    @Test
    fun empty() {
        // Arrange
        val clock = mockClock(nowDate = "2023-11-09")
        val availabilities = listOf<ProfessionalAvailability>()
        // Act
        val actual = availabilities.mapToUi(clock)
        // Assert
        // @formatter:off
        val expected = listOf(
            listOf("",      "Thu\n9", "Fri\n10", "Sat\n11", "Sun\n12", "Mon\n13", "Tue\n14"),
            listOf("00-04", "0",        "0",       "0",       "0",      "0",        "0"),
            listOf("04-08", "0",        "0",       "0",       "0",      "0",        "0"),
            listOf("08-12", "0",        "0",       "0",       "0",      "0",        "0"),
            listOf("12-16", "0",        "0",       "0",       "0",      "0",        "0"),
            listOf("16-20", "0",        "0",       "0",       "0",      "0",        "0"),
            listOf("20-24", "0",        "0",       "0",       "0",      "0",        "0"),
        )
        // @formatter:on
        assertEquals(expected, actual)
    }

}
