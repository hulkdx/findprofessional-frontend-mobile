package com.hulkdx.findprofessional.core.model.proauth

import kotlin.test.Test
import kotlin.test.assertEquals

class PriceUtilsTest {
    @Test
    fun testToPriceNumber() {
        val testData = listOf(
            "" to null,
            "0" to 0,
            "50" to 5000,
            "100" to 10000,
            "200" to 20000,
            "50.25" to 5025,
            "50.252" to 5025,
            "50.259" to 5025,
        )

        testData.forEach { (priceString, expected) ->
            // Arrange
            // Act
            val result = PriceUtils.toPriceNumber(priceString)
            // Assert
            assertEquals(expected, result)
        }
    }

    @Test
    fun testToPriceString() {
        val testData = listOf(
            0 to "0",
            5000 to "50",
            10000 to "100",
            20000 to "200",
            5025 to "50.25",
        )

        testData.forEach { (priceNumber, expected) ->
            // Arrange
            // Act
            val result = PriceUtils.toPriceString(priceNumber)
            // Assert
            assertEquals(expected, result)
        }
    }
}
