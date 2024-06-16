package com.hulkdx.findprofessional.common.utils

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource

@Composable
fun StringOrRes.localized(): String {
    return string ?: stringResource(res!!)
}
