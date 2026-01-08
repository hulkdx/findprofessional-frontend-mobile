package com.hulkdx.findprofessional.core.utils.clipboard

import android.content.ClipData
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.toClipEntry

actual fun createClipEntry(string: String): ClipEntry {
    return ClipData.newPlainText(string, string).toClipEntry()
}
