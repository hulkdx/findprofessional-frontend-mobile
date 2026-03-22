package com.hulkdx.findprofessional.core.network

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object DebugApiUrl : ApiUrl, KoinComponent {
    private val baseUrlProvider: DebugBaseUrlProvider by inject()

    override fun of(path: String): String {
        return "${baseUrlProvider.baseUrl(path)}/$path"
    }
}
