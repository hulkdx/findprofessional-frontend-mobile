package com.hulkdx.findprofessional.tools.screenshot.tests

import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalReview
import com.hulkdx.findprofessional.core.features.user.User
import com.hulkdx.findprofessional.feature.book.time.BookingTimeUiState
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

val randomDate = LocalDate.parse("2023-11-10")

val reviews = listOf(
    ProfessionalReview(
        id = 0,
        user = User(
            profileImage = null,
            firstName = "Stefan",
            lastName = "Holman",
            email = "",
        ),
        rate = 5,
        contentText = "He was a great coach for me!",
        createdAt = Clock.System.now(),
        updatedAt = Clock.System.now(),
    ),
    ProfessionalReview(
        id = 1,
        user = User(
            profileImage = null,
            firstName = "Bill",
            lastName = "Gates",
            email = "",
        ),
        rate = 1,
        contentText = "I love it, but it was bad.",
        createdAt = Clock.System.now(),
        updatedAt = Clock.System.now(),
    )
)

val professionals = listOf(
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
        "Former professional boxer who competed from 1985 to 2005",
        availability = listOf(
            ProfessionalAvailability(
                id = 1,
                date = randomDate,
                from = LocalTime.parse("08:00"),
                to = LocalTime.parse("08:30"),
            ),
            ProfessionalAvailability(
                id = 2,
                date = randomDate,
                from = LocalTime.parse("09:00"),
                to = LocalTime.parse("10:30"),
            ),
            ProfessionalAvailability(
                id = 3,
                date = LocalDate.parse("2023-11-10"),
                from = LocalTime.parse("07:00"),
                to = LocalTime.parse("08:00"),
            ),
            ProfessionalAvailability(
                id = 4,
                date = LocalDate.parse("2023-11-11"),
                from = LocalTime.parse("09:00"),
                to = LocalTime.parse("11:00"),
            ),
            ProfessionalAvailability(
                id = 5,
                date = LocalDate.parse("2023-11-12"),
                from = LocalTime.parse("12:00"),
                to = LocalTime.parse("15:00"),
            ),
            ProfessionalAvailability(
                id = 6,
                date = LocalDate.parse("2023-11-13"),
                from = LocalTime.parse("20:00"),
                to = LocalTime.parse("00:00"),
            ),
        ),
        reviewSize = "100",
        reviews = reviews
    ),
    Professional(
        2,
        "test2@email.com",
        "Naomi",
        "Spence",
        "Life coach",
        200,
        "EUR",
        "https://i.imgur.com/FVABZOc.jpeg",
        null,
        "One notable actress who graced the screens from 1985 to 2005 is Meryl Streep. Widely regarded as one of the greatest actresses of her generation, Streep's career during this period was marked by an exceptional range and versatility in her performances.",
        availability = listOf(
            ProfessionalAvailability(
                id = 1,
                date = LocalDate.parse("2023-11-09"),
                from = LocalTime.parse("08:00"),
                to = LocalTime.parse("09:00"),
            ),
            ProfessionalAvailability(
                id = 2,
                date = LocalDate.parse("2023-11-10"),
                from = LocalTime.parse("07:00"),
                to = LocalTime.parse("08:00"),
            ),
            ProfessionalAvailability(
                id = 3,
                date = LocalDate.parse("2023-11-11"),
                from = LocalTime.parse("09:00"),
                to = LocalTime.parse("11:00"),
            ),
            ProfessionalAvailability(
                id = 4,
                date = LocalDate.parse("2023-11-12"),
                from = LocalTime.parse("12:00"),
                to = LocalTime.parse("15:00"),
            ),
            ProfessionalAvailability(
                id = 5,
                date = LocalDate.parse("2023-11-13"),
                from = LocalTime.parse("20:00"),
                to = LocalTime.parse("00:00"),
            ),
        ),
        reviewSize = "100",
        reviews = reviews,
    ),
    Professional(
        3,
        "test2@email.com",
        "Naomi",
        "Spence",
        "Life coach",
        200,
        "EUR",
        "https://i.imgur.com/FVABZOc.jpeg",
        null,
        "One notable actress who graced the screens from 1985 to 2005 is Meryl Streep. Widely regarded as one of the greatest actresses of her generation, Streep's career during this period was marked by an exceptional range and versatility in her performances.",
        availability = listOf(),
        reviewSize = "100",
        reviews = listOf(),
    ),
    Professional(
        4,
        "test2@email.com",
        "Naomi",
        "Spence",
        "Life coach",
        200,
        "EUR",
        "https://i.imgur.com/FVABZOc.jpeg",
        null,
        "One notable actress who graced the screens from 1985 to 2005 is Meryl Streep. Widely regarded as one of the greatest actresses of her generation, Streep's career during this period was marked by an exceptional range and versatility in her performances.",
        availability = listOf(),
        reviewSize = "100",
        reviews = listOf(),
    )
)

val bookingTime = BookingTimeUiState(
    currentDate = "3.2.2024",
    times = listOf(
        BookingTimeUiState.BookingTime(
            id = 0,
            startTime = "00:00",
            endTime = "00:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.Available,
        ), BookingTimeUiState.BookingTime(
            id = 30,
            startTime = "00:30",
            endTime = "01:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.Selected,
        ), BookingTimeUiState.BookingTime(
            id = 60,
            startTime = "01:00",
            endTime = "01:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 90,
            startTime = "01:30",
            endTime = "02:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 120,
            startTime = "02:00",
            endTime = "02:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 150,
            startTime = "02:30",
            endTime = "03:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 180,
            startTime = "03:00",
            endTime = "03:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 210,
            startTime = "03:30",
            endTime = "04:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 240,
            startTime = "04:00",
            endTime = "04:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 270,
            startTime = "04:30",
            endTime = "05:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 300,
            startTime = "05:00",
            endTime = "05:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 330,
            startTime = "05:30",
            endTime = "06:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 360,
            startTime = "06:00",
            endTime = "06:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 390,
            startTime = "06:30",
            endTime = "07:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 420,
            startTime = "07:00",
            endTime = "07:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 450,
            startTime = "07:30",
            endTime = "08:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 480,
            startTime = "08:00",
            endTime = "08:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 510,
            startTime = "08:30",
            endTime = "09:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 540,
            startTime = "09:00",
            endTime = "09:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 570,
            startTime = "09:30",
            endTime = "10:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 600,
            startTime = "10:00",
            endTime = "10:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 630,
            startTime = "10:30",
            endTime = "11:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 660,
            startTime = "11:00",
            endTime = "11:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 690,
            startTime = "11:30",
            endTime = "12:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 720,
            startTime = "12:00",
            endTime = "12:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 750,
            startTime = "12:30",
            endTime = "13:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 780,
            startTime = "13:00",
            endTime = "13:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 810,
            startTime = "13:30",
            endTime = "14:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 840,
            startTime = "14:00",
            endTime = "14:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 870,
            startTime = "14:30",
            endTime = "15:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 900,
            startTime = "15:00",
            endTime = "15:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 930,
            startTime = "15:30",
            endTime = "16:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 960,
            startTime = "16:00",
            endTime = "16:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 990,
            startTime = "16:30",
            endTime = "17:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1020,
            startTime = "17:00",
            endTime = "17:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1050,
            startTime = "17:30",
            endTime = "18:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1080,
            startTime = "18:00",
            endTime = "18:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1110,
            startTime = "18:30",
            endTime = "19:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1140,
            startTime = "19:00",
            endTime = "19:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1170,
            startTime = "19:30",
            endTime = "20:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1200,
            startTime = "20:00",
            endTime = "20:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1230,
            startTime = "20:30",
            endTime = "21:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1260,
            startTime = "21:00",
            endTime = "21:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1290,
            startTime = "21:30",
            endTime = "22:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1320,
            startTime = "22:00",
            endTime = "22:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1350,
            startTime = "22:30",
            endTime = "23:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1380,
            startTime = "23:00",
            endTime = "23:30",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1410,
            startTime = "23:30",
            endTime = "00:00",
            date = randomDate,
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        )
    )
        .chunked(2),
)
