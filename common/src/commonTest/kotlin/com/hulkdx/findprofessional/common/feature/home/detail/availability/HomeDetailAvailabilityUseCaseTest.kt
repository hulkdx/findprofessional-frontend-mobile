@file:Suppress("PrivatePropertyName")

package com.hulkdx.findprofessional.common.feature.home.detail.availability

import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalTime
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

    @Test
    fun bla() = runBlocking {
        val pro = MutableStateFlow(
            professional(
                listOf(
                    ProfessionalAvailability(
                        date = "2023-11-09".toLocalDate(),
                        from = "08:00".toLocalTime(),
                        to = "09:00".toLocalTime(),
                    ),
                    ProfessionalAvailability(
                        date = "2023-11-10".toLocalDate(),
                        from = "07:00".toLocalTime(),
                        to = "08:00".toLocalTime(),
                    ),
                    ProfessionalAvailability(
                        date = "2023-11-11".toLocalDate(),
                        from = "09:00".toLocalTime(),
                        to = "11:00".toLocalTime(),
                    ),
                    ProfessionalAvailability(
                        date = "2023-11-12".toLocalDate(),
                        from = "12:00".toLocalTime(),
                        to = "15:00".toLocalTime(),
                    ),
                    ProfessionalAvailability(
                        date = "2023-11-13".toLocalDate(),
                        from = "20:00".toLocalTime(),
                        to = "00:00".toLocalTime(),
                    ),
                )
            )
        )
        val d = MutableStateFlow(LocalDate(2023, 10, 1))
        launch {
            delay(2000)
            println("updating pro")
            pro.value = professional(
                listOf(
                    ProfessionalAvailability(
                        date = "2023-11-09".toLocalDate(),
                        from = "08:00".toLocalTime(),
                        to = "09:00".toLocalTime(),
                    ),
                )
            )
            delay(3000)
            println("updating date")
            d.value = LocalDate(2024, 11, 2)
        }
        sut.getAvailabilityData(pro, d)
            .collect {
                println("Got a new data $it")
            }
    }

    private fun professional(professionalAvailabilities: List<ProfessionalAvailability>) =
        Professional(
            1,
            "test@email.com",
            "Luba",
            "Mikaela",
            "Life coach",
            100,
            "EUR",
            "https://i.imgur.com/5Yma8Kl.jpeg",
            "5.0",
            "former professional boxer who competed from 1985 to 2005",
            availability = professionalAvailabilities,
            reviewSize = "100",
        )
}
