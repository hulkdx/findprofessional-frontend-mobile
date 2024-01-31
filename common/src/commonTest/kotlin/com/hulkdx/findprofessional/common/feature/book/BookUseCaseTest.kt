package com.hulkdx.findprofessional.common.feature.book

import com.hulkdx.findprofessional.common.feature.book.BookUiState.BookingTimes
import com.hulkdx.findprofessional.common.feature.book.BookUiState.BookingTimes.Type.Available
import com.hulkdx.findprofessional.common.feature.book.BookUiState.BookingTimes.Type.UnAvailable
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability
import com.hulkdx.findprofessional.common.utils.createProfessional
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class BookUseCaseTest {

    private lateinit var sut: BookUseCase

    @BeforeTest
    fun setUp() {
        sut = BookUseCase()
    }

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
                date = LocalDate.fromEpochDays(100), // irrelevant
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
    fun `getTimes when empty availability then result would be all unavailable times`() {
        // Arrange
        val professional = createProfessional(availability = listOf())
        val expectedResult = allUnavailable()
        // Act
        val result = sut.getTimes(professional)
        // Assert
        assertEquals(result, expectedResult)
    }

    @Test
    fun `getTimes when all times are available then result would be available`() {
        // Arrange
        val professional = createProfessionalWithAvailability(0 to 0)
        val expectedResult = allAvailable()
        // Act
        val result = sut.getTimes(professional)
        // Assert
        assertEquals(result, expectedResult)
    }

    private fun createProfessionalWithAvailability(vararg times: Pair<Int, Int>) =
        createProfessional(
            availability = times.map {
                ProfessionalAvailability(
                    date = LocalDate.fromEpochDays(100), // irrelevant
                    from = LocalTime.fromSecondOfDay(it.first * 60),
                    to = LocalTime.fromSecondOfDay(it.second * 60),
                )
            }
        )

    private fun allUnavailable() = listOf(
        BookingTimes(
            startTime = "00:00",
            endTime = "00:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "00:30",
            endTime = "01:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "01:00",
            endTime = "01:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "01:30",
            endTime = "02:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "02:00",
            endTime = "02:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "02:30",
            endTime = "03:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "03:00",
            endTime = "03:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "03:30",
            endTime = "04:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "04:00",
            endTime = "04:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "04:30",
            endTime = "05:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "05:00",
            endTime = "05:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "05:30",
            endTime = "06:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "06:00",
            endTime = "06:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "06:30",
            endTime = "07:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "07:00",
            endTime = "07:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "07:30",
            endTime = "08:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "08:00",
            endTime = "08:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "08:30",
            endTime = "09:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "09:00",
            endTime = "09:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "09:30",
            endTime = "10:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "10:00",
            endTime = "10:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "10:30",
            endTime = "11:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "11:00",
            endTime = "11:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "11:30",
            endTime = "12:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "12:00",
            endTime = "12:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "12:30",
            endTime = "13:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "13:00",
            endTime = "13:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "13:30",
            endTime = "14:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "14:00",
            endTime = "14:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "14:30",
            endTime = "15:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "15:00",
            endTime = "15:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "15:30",
            endTime = "16:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "16:00",
            endTime = "16:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "16:30",
            endTime = "17:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "17:00",
            endTime = "17:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "17:30",
            endTime = "18:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "18:00",
            endTime = "18:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "18:30",
            endTime = "19:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "19:00",
            endTime = "19:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "19:30",
            endTime = "20:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "20:00",
            endTime = "20:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "20:30",
            endTime = "21:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "21:00",
            endTime = "21:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "21:30",
            endTime = "22:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "22:00",
            endTime = "22:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "22:30",
            endTime = "23:00",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "23:00",
            endTime = "23:30",
            type = UnAvailable,
        ), BookingTimes(
            startTime = "23:30",
            endTime = "00:00",
            type = UnAvailable,
        )
    )
        .chunked(2)


    private fun allAvailable() = listOf(
        BookingTimes(
            startTime = "00:00",
            endTime = "00:30",
            type = Available,
        ), BookingTimes(
            startTime = "00:30",
            endTime = "01:00",
            type = Available,
        ), BookingTimes(
            startTime = "01:00",
            endTime = "01:30",
            type = Available,
        ), BookingTimes(
            startTime = "01:30",
            endTime = "02:00",
            type = Available,
        ), BookingTimes(
            startTime = "02:00",
            endTime = "02:30",
            type = Available,
        ), BookingTimes(
            startTime = "02:30",
            endTime = "03:00",
            type = Available,
        ), BookingTimes(
            startTime = "03:00",
            endTime = "03:30",
            type = Available,
        ), BookingTimes(
            startTime = "03:30",
            endTime = "04:00",
            type = Available,
        ), BookingTimes(
            startTime = "04:00",
            endTime = "04:30",
            type = Available,
        ), BookingTimes(
            startTime = "04:30",
            endTime = "05:00",
            type = Available,
        ), BookingTimes(
            startTime = "05:00",
            endTime = "05:30",
            type = Available,
        ), BookingTimes(
            startTime = "05:30",
            endTime = "06:00",
            type = Available,
        ), BookingTimes(
            startTime = "06:00",
            endTime = "06:30",
            type = Available,
        ), BookingTimes(
            startTime = "06:30",
            endTime = "07:00",
            type = Available,
        ), BookingTimes(
            startTime = "07:00",
            endTime = "07:30",
            type = Available,
        ), BookingTimes(
            startTime = "07:30",
            endTime = "08:00",
            type = Available,
        ), BookingTimes(
            startTime = "08:00",
            endTime = "08:30",
            type = Available,
        ), BookingTimes(
            startTime = "08:30",
            endTime = "09:00",
            type = Available,
        ), BookingTimes(
            startTime = "09:00",
            endTime = "09:30",
            type = Available,
        ), BookingTimes(
            startTime = "09:30",
            endTime = "10:00",
            type = Available,
        ), BookingTimes(
            startTime = "10:00",
            endTime = "10:30",
            type = Available,
        ), BookingTimes(
            startTime = "10:30",
            endTime = "11:00",
            type = Available,
        ), BookingTimes(
            startTime = "11:00",
            endTime = "11:30",
            type = Available,
        ), BookingTimes(
            startTime = "11:30",
            endTime = "12:00",
            type = Available,
        ), BookingTimes(
            startTime = "12:00",
            endTime = "12:30",
            type = Available,
        ), BookingTimes(
            startTime = "12:30",
            endTime = "13:00",
            type = Available,
        ), BookingTimes(
            startTime = "13:00",
            endTime = "13:30",
            type = Available,
        ), BookingTimes(
            startTime = "13:30",
            endTime = "14:00",
            type = Available,
        ), BookingTimes(
            startTime = "14:00",
            endTime = "14:30",
            type = Available,
        ), BookingTimes(
            startTime = "14:30",
            endTime = "15:00",
            type = Available,
        ), BookingTimes(
            startTime = "15:00",
            endTime = "15:30",
            type = Available,
        ), BookingTimes(
            startTime = "15:30",
            endTime = "16:00",
            type = Available,
        ), BookingTimes(
            startTime = "16:00",
            endTime = "16:30",
            type = Available,
        ), BookingTimes(
            startTime = "16:30",
            endTime = "17:00",
            type = Available,
        ), BookingTimes(
            startTime = "17:00",
            endTime = "17:30",
            type = Available,
        ), BookingTimes(
            startTime = "17:30",
            endTime = "18:00",
            type = Available,
        ), BookingTimes(
            startTime = "18:00",
            endTime = "18:30",
            type = Available,
        ), BookingTimes(
            startTime = "18:30",
            endTime = "19:00",
            type = Available,
        ), BookingTimes(
            startTime = "19:00",
            endTime = "19:30",
            type = Available,
        ), BookingTimes(
            startTime = "19:30",
            endTime = "20:00",
            type = Available,
        ), BookingTimes(
            startTime = "20:00",
            endTime = "20:30",
            type = Available,
        ), BookingTimes(
            startTime = "20:30",
            endTime = "21:00",
            type = Available,
        ), BookingTimes(
            startTime = "21:00",
            endTime = "21:30",
            type = Available,
        ), BookingTimes(
            startTime = "21:30",
            endTime = "22:00",
            type = Available,
        ), BookingTimes(
            startTime = "22:00",
            endTime = "22:30",
            type = Available,
        ), BookingTimes(
            startTime = "22:30",
            endTime = "23:00",
            type = Available,
        ), BookingTimes(
            startTime = "23:00",
            endTime = "23:30",
            type = Available,
        ), BookingTimes(
            startTime = "23:30",
            endTime = "00:00",
            type = Available,
        )
    )
        .chunked(2)
}
