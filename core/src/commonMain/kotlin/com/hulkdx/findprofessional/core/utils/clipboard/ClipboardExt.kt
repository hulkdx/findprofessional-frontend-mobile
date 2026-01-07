package com.hulkdx.findprofessional.core.utils.clipboard

import androidx.compose.ui.platform.ClipEntry

expect fun createClipEntry(string: String): ClipEntry
