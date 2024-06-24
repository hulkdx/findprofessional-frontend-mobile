package com.hulkdx.findprofessional.common.utils

import com.hulkdx.findprofessional.common.feature.home.model.Currency


object CurrencyFormatter {
    fun toSymbol(currency: String?): String {
        return runCatching { Currency.valueOf(currency ?: "") }
            .getOrElse { Currency.EUR }
            .symbol
    }
}
