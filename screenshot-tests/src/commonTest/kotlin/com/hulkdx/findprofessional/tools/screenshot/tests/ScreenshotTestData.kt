package com.hulkdx.findprofessional.tools.screenshot.tests

import com.hulkdx.findprofessional.feature.pro.model.Professional
import com.hulkdx.findprofessional.feature.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.feature.pro.model.ProfessionalReview
import com.hulkdx.findprofessional.feature.authentication.model.user.User
import com.hulkdx.findprofessional.feature.booking.time.BookingTimeUiState
import kotlinx.datetime.LocalDate
import kotlin.time.Instant

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
        createdAt = Instant.parse("2025-11-11T09:00:00Z"),
        updatedAt = Instant.parse("2025-11-11T09:00:00Z"),
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
        createdAt = Instant.parse("2025-11-11T09:00:00Z"),
        updatedAt = Instant.parse("2025-11-11T09:00:00Z"),
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
                from = Instant.parse("2023-11-10T08:00:00Z"),
                to = Instant.parse("2023-11-10T08:30:00Z"),
            ),
            ProfessionalAvailability(
                id = 2,
                from = Instant.parse("2023-11-10T09:00:00Z"),
                to = Instant.parse("2023-11-10T10:30:00Z"),
            ),
            ProfessionalAvailability(
                id = 3,
                from = Instant.parse("2023-11-10T07:00:00Z"),
                to = Instant.parse("2023-11-10T08:00:00Z"),
            ),
            ProfessionalAvailability(
                id = 4,
                from = Instant.parse("2023-11-11T09:00:00Z"),
                to = Instant.parse("2023-11-11T11:00:00Z"),
            ),
            ProfessionalAvailability(
                id = 5,
                from = Instant.parse("2023-11-12T12:00:00Z"),
                to = Instant.parse("2023-11-12T15:00:00Z"),
            ),
            ProfessionalAvailability(
                id = 6,
                from = Instant.parse("2023-11-13T20:00:00Z"),
                to = Instant.parse("2023-11-14T00:00:00Z"),
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
                from = Instant.parse("2023-11-09T08:00:00Z"),
                to = Instant.parse("2023-11-09T09:00:00Z"),
            ),
            ProfessionalAvailability(
                id = 2,
                from = Instant.parse("2023-11-10T07:00:00Z"),
                to = Instant.parse("2023-11-10T08:00:00Z"),
            ),
            ProfessionalAvailability(
                id = 3,
                from = Instant.parse("2023-11-11T09:00:00Z"),
                to = Instant.parse("2023-11-11T11:00:00Z"),
            ),
            ProfessionalAvailability(
                id = 4,
                from = Instant.parse("2023-11-12T12:00:00Z"),
                to = Instant.parse("2023-11-12T15:00:00Z"),
            ),
            ProfessionalAvailability(
                id = 5,
                from = Instant.parse("2023-11-13T20:00:00Z"),
                to = Instant.parse("2023-11-14T00:00:00Z"),
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
            type = BookingTimeUiState.BookingTime.Type.Available,
        ), BookingTimeUiState.BookingTime(
            id = 30,
            startTime = "00:30",
            endTime = "01:00",
            type = BookingTimeUiState.BookingTime.Type.Selected,
        ), BookingTimeUiState.BookingTime(
            id = 60,
            startTime = "01:00",
            endTime = "01:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 90,
            startTime = "01:30",
            endTime = "02:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 120,
            startTime = "02:00",
            endTime = "02:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 150,
            startTime = "02:30",
            endTime = "03:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 180,
            startTime = "03:00",
            endTime = "03:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 210,
            startTime = "03:30",
            endTime = "04:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 240,
            startTime = "04:00",
            endTime = "04:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 270,
            startTime = "04:30",
            endTime = "05:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 300,
            startTime = "05:00",
            endTime = "05:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 330,
            startTime = "05:30",
            endTime = "06:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 360,
            startTime = "06:00",
            endTime = "06:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 390,
            startTime = "06:30",
            endTime = "07:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 420,
            startTime = "07:00",
            endTime = "07:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 450,
            startTime = "07:30",
            endTime = "08:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 480,
            startTime = "08:00",
            endTime = "08:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 510,
            startTime = "08:30",
            endTime = "09:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 540,
            startTime = "09:00",
            endTime = "09:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 570,
            startTime = "09:30",
            endTime = "10:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 600,
            startTime = "10:00",
            endTime = "10:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 630,
            startTime = "10:30",
            endTime = "11:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 660,
            startTime = "11:00",
            endTime = "11:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 690,
            startTime = "11:30",
            endTime = "12:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 720,
            startTime = "12:00",
            endTime = "12:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 750,
            startTime = "12:30",
            endTime = "13:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 780,
            startTime = "13:00",
            endTime = "13:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 810,
            startTime = "13:30",
            endTime = "14:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 840,
            startTime = "14:00",
            endTime = "14:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 870,
            startTime = "14:30",
            endTime = "15:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 900,
            startTime = "15:00",
            endTime = "15:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 930,
            startTime = "15:30",
            endTime = "16:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 960,
            startTime = "16:00",
            endTime = "16:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 990,
            startTime = "16:30",
            endTime = "17:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1020,
            startTime = "17:00",
            endTime = "17:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1050,
            startTime = "17:30",
            endTime = "18:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1080,
            startTime = "18:00",
            endTime = "18:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1110,
            startTime = "18:30",
            endTime = "19:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1140,
            startTime = "19:00",
            endTime = "19:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1170,
            startTime = "19:30",
            endTime = "20:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1200,
            startTime = "20:00",
            endTime = "20:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1230,
            startTime = "20:30",
            endTime = "21:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1260,
            startTime = "21:00",
            endTime = "21:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1290,
            startTime = "21:30",
            endTime = "22:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1320,
            startTime = "22:00",
            endTime = "22:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1350,
            startTime = "22:30",
            endTime = "23:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1380,
            startTime = "23:00",
            endTime = "23:30",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        ), BookingTimeUiState.BookingTime(
            id = 1410,
            startTime = "23:30",
            endTime = "00:00",
            type = BookingTimeUiState.BookingTime.Type.UnAvailable,
        )
    )
        .chunked(2),
)
