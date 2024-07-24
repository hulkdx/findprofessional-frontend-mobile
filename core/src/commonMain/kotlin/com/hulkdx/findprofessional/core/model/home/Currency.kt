package com.hulkdx.findprofessional.core.model.home


enum class Currency(
    val currencyShortName: String,
    val symbol: String,
) {
    EUR("EUR", "€"),
    USD("USD", "$"),
}
