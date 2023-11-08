package com.hulkdx.findprofessional.common.feature.home.utils

import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability
import com.hulkdx.findprofessional.common.utils.mockClock
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalTime
import kotlin.test.Test
import kotlin.test.assertEquals


class ProfessionalAvailabilityUiMapperTest {

    @Test
    fun `mapToUi empty`() {
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

    @Test
    fun `mapToUi tests`() {
        // Arrange
        val clock = mockClock(nowDate = "2023-11-09")
        val availabilities = listOf(
            av("2023-11-09", "08:00", "09:00"),
            av("2023-11-10", "07:00", "08:00"),
            av("2023-11-11", "09:00", "11:00"),
            av("2023-11-12", "12:00", "15:00"),
            av("2023-11-13", "20:00", "00:00"),
        )
        // Act
        val actual = availabilities.mapToUi(clock)
        // Assert
        // @formatter:off
        val expected = listOf(
            listOf("",      "Thu\n9", "Fri\n10", "Sat\n11", "Sun\n12", "Mon\n13", "Tue\n14"),
            listOf("00-04", "0",        "0",       "0",       "0",      "0",        "0"),
            listOf("04-08", "0",        "1",       "0",       "0",      "0",        "0"),
            listOf("08-12", "1",        "0",       "2",       "0",      "0",        "0"),
            listOf("12-16", "0",        "0",       "0",       "3",      "0",        "0"),
            listOf("16-20", "0",        "0",       "0",       "0",      "0",        "0"),
            listOf("20-24", "0",        "0",       "0",       "0",      "4",        "0"),
        )
        // @formatter:on
        assertEquals(expected, actual)
    }

    @Test
    fun `calculateDuration tests`() {
        data class TestData(val av: ProfessionalAvailability, val index: Int, val expected: String)
        val testData = listOf(
            TestData(av("00:00", "04:00"), 0, "4"),
            TestData(av("01:00", "04:00"), 0, "3"),
            TestData(av("02:00", "04:00"), 0, "2"),
            TestData(av("03:00", "04:00"), 0, "1"),
            TestData(av("04:00", "04:00"), 0, "0"),

            TestData(av("00:00", "04:00"), 1, "0"),
            TestData(av("01:00", "04:00"), 1, "0"),
            TestData(av("02:00", "04:00"), 1, "0"),
            TestData(av("03:00", "04:00"), 1, "0"),
            TestData(av("04:00", "04:00"), 1, "0"),

            TestData(av("04:00", "08:00"), 1, "4"),
            TestData(av("05:00", "08:00"), 1, "3"),
            TestData(av("06:00", "08:00"), 1, "2"),
            TestData(av("07:00", "08:00"), 1, "1"),
            TestData(av("08:00", "08:00"), 1, "0"),

            TestData(av("00:00", "23:00"), 0, "4"),
            TestData(av("00:00", "23:00"), 1, "4"),
            TestData(av("00:00", "23:00"), 2, "4"),
            TestData(av("00:00", "23:00"), 3, "4"),
            TestData(av("00:00", "23:00"), 4, "4"),
            TestData(av("00:00", "23:00"), 5, "3"),

            TestData(av("20:00", "00:00"), 5, "4"),
            TestData(av("23:00", "00:00"), 5, "1"),
        )
        for (t in testData) {
            // Act
            val actual = calculateDuration(t.av, t.index)
            // Assert
            assertEquals(t.expected, actual)
        }
    }

    private fun av(from: String, to: String): ProfessionalAvailability {
        val randomDate = "2023-11-09"
        return av(randomDate, from, to)
    }

    private fun av(date: String, from: String, to: String): ProfessionalAvailability {
        return ProfessionalAvailability(
            date = date.toLocalDate(),
            from = from.toLocalTime(),
            to = to.toLocalTime(),
        )
    }
}
