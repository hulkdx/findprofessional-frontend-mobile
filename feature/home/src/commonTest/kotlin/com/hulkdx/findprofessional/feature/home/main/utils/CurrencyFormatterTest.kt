package com.hulkdx.findprofessional.feature.home.main.utils

import kotlin.test.Test
import kotlin.test.assertEquals

class CurrencyFormatterTest {

    @Test
    fun testUnknown() {
        // Arrange
        val expected = "€"
        // Act
        val result = CurrencyFormatter.toSymbol("What is this currency")
        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun testEuro() {
        // Arrange
        val expected = "€"
        // Act
        val result = CurrencyFormatter.toSymbol("EUR")
        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun testDollar() {
        // Arrange
        val expected = "$"
        // Act
        val result = CurrencyFormatter.toSymbol("USD")
        // Assert
        assertEquals(expected, result)
    }
}
