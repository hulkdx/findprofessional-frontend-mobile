package com.hulkdx.findprofessional.feature.developer

import com.hulkdx.findprofessional.core.network.DebugBaseUrlProvider
import com.hulkdx.findprofessional.core.network.PROD_BASE_URL
import com.hulkdx.findprofessional.core.platform.PlatformSpecific
import com.hulkdx.findprofessional.feature.developer.storage.DeveloperStorage
import kotlinx.coroutines.runBlocking

class DebugBaseUrlProviderImpl(
    private val developerStorage: DeveloperStorage,
    private val platformSpecific: PlatformSpecific,
) : DebugBaseUrlProvider {
    private val shouldUseProd by lazy {
        runBlocking {
            runCatching { developerStorage.getAsBoolean(DeveloperStorage.Key.UseProductionBaseUrl) }
                .getOrNull() ?: false
        }
    }
    private val localhost by lazy {
        platformSpecific.localhostUrl()
    }

    override fun baseUrl(path: String): String {
        return if (shouldUseProd) {
            PROD_BASE_URL
        } else {
            "http://$localhost${getPort(path)}"
        }
    }

    private fun getPort(path: String): String {
        return when (path) {
            "auth/login",
            "auth/register",
            "auth/refresh-token",
            "auth/user",
            "auth/refresh",
                -> ":8080"

            "payments/start" -> ":8082"

            else -> ":8081"
        }
    }

}
