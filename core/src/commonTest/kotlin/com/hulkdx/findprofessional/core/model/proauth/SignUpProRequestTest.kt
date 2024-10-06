package com.hulkdx.findprofessional.core.model.proauth

import kotlin.test.Test
import kotlin.test.assertEquals

class SignUpProRequestTest {
    @Test
    fun testPriceConversion() {
        val testData = listOf(
            "" to null,
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
            val result = SignUpProRequest(priceString = priceString).price
            // Assert
            assertEquals(expected, result)
        }
    }
}
