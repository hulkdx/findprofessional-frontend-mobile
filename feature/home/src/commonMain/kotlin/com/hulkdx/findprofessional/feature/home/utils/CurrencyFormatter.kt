package com.hulkdx.findprofessional.feature.home.utils

import com.hulkdx.findprofessional.feature.home.model.Currency

object CurrencyFormatter {
    fun toSymbol(currency: String?): String {
        return runCatching { Currency.valueOf(currency ?: "") }
            .getOrElse { Currency.EUR }
            .symbol
    }
}
