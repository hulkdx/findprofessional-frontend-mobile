package com.hulkdx.findprofessional.core.utils.clipboard

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ClipEntry

@OptIn(ExperimentalComposeUiApi::class)
actual fun createClipEntry(string: String): ClipEntry {
    return ClipEntry.withPlainText(string)
}
