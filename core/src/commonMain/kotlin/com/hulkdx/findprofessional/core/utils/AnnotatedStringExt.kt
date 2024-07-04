package com.hulkdx.findprofessional.core.utils

import androidx.compose.ui.text.AnnotatedString.Builder
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

inline fun <R : Any> Builder.bold(block: Builder.() -> R) =
    withStyle(SpanStyle(fontWeight = FontWeight.Bold), block)

//@Composable
//fun Builder.append(@StringRes id: Int) = append(stringResource(id = id))
