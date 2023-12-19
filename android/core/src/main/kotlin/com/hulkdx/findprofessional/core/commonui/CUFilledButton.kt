package com.hulkdx.findprofessional.core.commonui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body1
import com.hulkdx.findprofessional.core.theme.body1Medium

@Composable
fun CUFilledButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(
            text = text,
            style = body1Medium,
        )
    }
}

@Preview
@Composable
private fun CUFilledButtonPreview() {
    AppTheme {
        CUFilledButton(
            Modifier,
            "Button"
        ) {}
    }
}
