package com.hulkdx.findprofessional.core.model.proauth

import com.hulkdx.findprofessional.core.utils.PriceUtils
import kotlin.test.Test
import kotlin.test.assertEquals

class PriceUtilsTest {
    @Test
    fun testToPriceNumber() {
        val testData = listOf(
            "" to null,
            "0" to 0L,
            "50" to 5000L,
            "100" to 10000L,
            "200" to 20000L,
            "50.25" to 5025L,
            "50.252" to 5025L,
            "50.259" to 5025L,
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
            0L to "0",
            5000L to "50",
            10000L to "100",
            20000L to "200",
            5025L to "50.25",
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
