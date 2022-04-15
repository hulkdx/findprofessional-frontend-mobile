package com.hulkdx.findprofessional.core.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle


inline fun <R : Any> AnnotatedString.Builder.bold(
    crossinline block: AnnotatedString.Builder.() -> R
): R {
    return withStyle(SpanStyle(fontWeight = FontWeight.Bold), block)
}
