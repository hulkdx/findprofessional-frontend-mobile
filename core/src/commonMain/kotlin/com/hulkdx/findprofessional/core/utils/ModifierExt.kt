package com.hulkdx.findprofessional.core.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp


fun Modifier.borderTop(width: Dp, color: Color) = drawBehind {
    val strokeWidth = width.toPx()
    val y = strokeWidth / 2
    drawLine(
        color = color,
        start = androidx.compose.ui.geometry.Offset(0f, y),
        end = androidx.compose.ui.geometry.Offset(size.width, y),
        strokeWidth = strokeWidth
    )
}
