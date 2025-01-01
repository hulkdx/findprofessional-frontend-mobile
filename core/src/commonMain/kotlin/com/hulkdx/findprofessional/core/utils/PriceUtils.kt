package com.hulkdx.findprofessional.core.utils

object PriceUtils {
    fun toPriceString(priceNumber: Long): String {
        // Convert to a float value
        val priceFloat = priceNumber.toFloat() / 100

        // Check if it's a whole number
        return if (priceFloat % 1 == 0f) {
            // Return only the integer part if there's no fractional part
            priceFloat.toInt().toString()
        } else {
            // Otherwise, round and format with two decimal places
            val roundedPrice = (priceFloat * 100).toInt() / 100.0
            val integerPart = roundedPrice.toInt()
            val decimalPart = ((roundedPrice - integerPart) * 100).toInt()
            "$integerPart.${decimalPart.toString().padStart(2, '0')}"
        }
    }


    fun toPriceNumber(priceString: String) =
        priceString.toFloatOrNull()?.times(100)?.toLong()

}
