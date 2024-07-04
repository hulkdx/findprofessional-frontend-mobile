package com.hulkdx.findprofessional.common.feature.home.model


enum class Currency(
    val currencyShortName: String,
    val symbol: String,
) {
    EUR("EUR", "€"),
    USD("USD", "$"),
}
