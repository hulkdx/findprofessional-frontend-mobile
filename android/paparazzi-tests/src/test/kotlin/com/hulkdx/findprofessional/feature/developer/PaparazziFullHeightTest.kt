package com.hulkdx.findprofessional.feature.developer

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.hulkdx.findprofessional.InMemoryApi
import com.hulkdx.findprofessional.feature.home.detail.HomeDetailScreen
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
                professional = InMemoryApi.professionals[0],
                // @formatter:off
                availability = listOf(
                    listOf("", "Thu\n19", "Fri\n20", "Sat\n21", "Sun\n22", "Mon\n23", "Tue\n24"),
                    listOf("00-04", "0", "0", "0", "0", "0", "0"),
                    listOf("04-08", "0", "1", "0", "0", "0", "0"),
                    listOf("08-12", "0", "0", "2", "0", "0", "0"),
                    listOf("12-16", "0", "0", "0", "3", "0", "0"),
                    listOf("16-20", "0", "0", "0", "0", "0", "0"),
                    listOf("20-24", "0", "0", "0", "0", "4", "0"),
                ),
                // @formatter:on
                {},
                {},
            )
        }
    }
}
