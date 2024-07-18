package com.hulkdx.findprofessional.core.commonui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body2
import org.jetbrains.compose.ui.tooling.preview.Preview

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
            style = body2,
            color = MaterialTheme.colorScheme.onErrorContainer
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
