package com.hulkdx.findprofessional.tools.screenshot.tests

import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.core.model.pro.ProfessionalAvailability
import com.hulkdx.findprofessional.core.model.pro.ProfessionalReview
import com.hulkdx.findprofessional.core.model.user.User
import com.hulkdx.findprofessional.core.utils.now
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime


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
                date = LocalDate.now(),
                from = LocalTime.parse("08:00"),
                to = LocalTime.parse("08:30"),
            ),
            ProfessionalAvailability(
                date = LocalDate.now(),
                from = LocalTime.parse("09:00"),
                to = LocalTime.parse("10:30"),
            ),
            ProfessionalAvailability(
                date = LocalDate.parse("2023-11-10"),
                from = LocalTime.parse("07:00"),
                to = LocalTime.parse("08:00"),
            ),
            ProfessionalAvailability(
                date = LocalDate.parse("2023-11-11"),
                from = LocalTime.parse("09:00"),
                to = LocalTime.parse("11:00"),
            ),
            ProfessionalAvailability(
                date = LocalDate.parse("2023-11-12"),
                from = LocalTime.parse("12:00"),
                to = LocalTime.parse("15:00"),
            ),
            ProfessionalAvailability(
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
                date = LocalDate.parse("2023-11-09"),
                from = LocalTime.parse("08:00"),
                to = LocalTime.parse("09:00"),
            ),
            ProfessionalAvailability(
                date = LocalDate.parse("2023-11-10"),
                from = LocalTime.parse("07:00"),
                to = LocalTime.parse("08:00"),
            ),
            ProfessionalAvailability(
                date = LocalDate.parse("2023-11-11"),
                from = LocalTime.parse("09:00"),
                to = LocalTime.parse("11:00"),
            ),
            ProfessionalAvailability(
                date = LocalDate.parse("2023-11-12"),
                from = LocalTime.parse("12:00"),
                to = LocalTime.parse("15:00"),
            ),
            ProfessionalAvailability(
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


