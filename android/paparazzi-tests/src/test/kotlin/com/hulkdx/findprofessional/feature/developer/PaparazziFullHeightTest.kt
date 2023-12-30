package com.hulkdx.findprofessional.feature.developer

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.hulkdx.findprofessional.common.feature.authentication.model.User
import com.hulkdx.findprofessional.common.feature.home.detail.availability.AvailabilityData
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalReview
import com.hulkdx.findprofessional.feature.home.detail.HomeDetailScreen
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import org.junit.Rule
import org.junit.Test

class PaparazziFullHeightTest {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.NEXUS_5.copy(screenHeight = 5000)
    )

    @Test
    fun `HomeDetailScreen paparazzi test`() {
        paparazzi.paparazziTest {
            HomeDetailScreen(
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
                    availability = listOf(),
                    reviewSize = "100",
                    reviews = listOf(
                        ProfessionalReview(
                            id = 0,
                            user = User(
                                profileImage = "",
                                firstName = "Stefan",
                                lastName = "Holman",
                                email = "",
                            ),
                            rate = 4,
                            contentText = "Authentic and Wonderful 12-days tour of Paris. 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris.\nfeeling like I’ve learned a lot.",
                            createdAt = Clock.System.now(),
                            updatedAt = Clock.System.now(),
                        )
                    ),
                ),
                availability = AvailabilityData(
                    "January 2022",
                    5,
                    31,
                    LocalDate(2022, 1, 6),
                    listOf(
                        LocalDate(2022, 1, 6),
                        LocalDate(2022, 1, 7),
                        LocalDate(2022, 1, 12),
                    )
                ),
                {},
                {},
                {},
                {},
            )
        }
    }
}
