package com.hulkdx.findprofessional.core.commonui

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body1

@Composable
fun CUTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = body1,
            color = Color(0xFF09B0B9)
        )
    }
}

@Preview
@Composable
private fun CUTextButtonPreview() {
    AppTheme {
        CUTextButton(
            Modifier,
            "Button"
        ) {}
    }
}
