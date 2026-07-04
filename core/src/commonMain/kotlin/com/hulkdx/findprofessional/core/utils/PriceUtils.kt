package com.hulkdx.findprofessional.core.utils

import kotlin.math.abs

object PriceUtils {
    fun toPriceString(priceNumber: Long): String {
        val sign = if (priceNumber < 0) "-" else ""
        val absValue = abs(priceNumber)
        val whole = absValue / 100
        val cents = absValue % 100
        return if (cents == 0L) {
            "$sign$whole"
        } else {
            "$sign$whole.${cents.toString().padStart(2, '0')}"
        }
    }


    fun toPriceNumber(priceString: String) =
        priceString.toFloatOrNull()?.times(100)?.toLong()

}
