package com.hulkdx.findprofessional.feature.home.model


enum class Currency(
    val currencyShortName: String,
    val symbol: String,
) {
    EUR("EUR", "€"),
    USD("USD", "$"),
}
