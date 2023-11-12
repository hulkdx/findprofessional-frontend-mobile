package com.hulkdx.findprofessional.core.utils

import androidx.compose.runtime.Composable
import com.hulkdx.findprofessional.common.config.PlatformSpecific
import org.koin.compose.koinInject


@Composable
fun isDebug(): Boolean {
    val platformSpecific: PlatformSpecific = koinInject()
    return platformSpecific.isDebug()
}
