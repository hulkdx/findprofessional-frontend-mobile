package com.hulkdx.findprofessional.common.utils


object CurrencyFormatter {
    fun toSymbol(currency: String): String {
        return when (currency) {
            "USD" -> "$"
            "EUR" -> "€"
            else -> "€"
        }
    }
}
