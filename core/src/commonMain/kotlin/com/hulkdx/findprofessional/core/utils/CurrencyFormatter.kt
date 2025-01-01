package com.hulkdx.findprofessional.core.utils

import com.hulkdx.findprofessional.core.features.home.Currency


object CurrencyFormatter {
    fun toSymbol(currency: String?): String {
        return runCatching { Currency.valueOf(currency ?: "") }
            .getOrElse { Currency.EUR }
            .symbol
    }
}
