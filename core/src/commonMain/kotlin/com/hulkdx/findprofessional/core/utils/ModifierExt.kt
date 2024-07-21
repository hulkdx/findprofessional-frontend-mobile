package com.hulkdx.findprofessional.core.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp


fun Modifier.borderTop(width: Dp, color: Color) = drawBehind {
    val strokeWidth = width.toPx()
    drawLine(
        color = color,
        start = Offset(0f, 0f),
        end = Offset(size.width, 0f),
        strokeWidth = strokeWidth
    )
}
