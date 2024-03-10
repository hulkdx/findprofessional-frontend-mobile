package com.hulkdx.findprofessional.core.commonui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body1

@Composable
fun CUTextField(
    modifier: Modifier = Modifier,
    hint: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    value: String,
    onValueChanged: (String) -> (Unit),
    singleLine: Boolean = true,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        singleLine = singleLine,
        shape = RoundedCornerShape(8.dp),
        placeholder = {
            Text(
                text = hint,
                style = body1,
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        visualTransformation = visualTransformation,
    )
}

@Preview
@Composable
private fun CUTextFieldPreview() {
    AppTheme {
        CUTextField(
            modifier = Modifier,
            hint = "Hint",
            visualTransformation = VisualTransformation.None,
            value = "",
            onValueChanged = {}
        )
    }
}

@Preview
@Composable
private fun CUTextFieldValuePreview() {
    AppTheme {
        CUTextField(
            modifier = Modifier,
            hint = "",
            visualTransformation = VisualTransformation.None,
            value = "Value",
            onValueChanged = {}
        )
    }
}

@Preview
@Composable
private fun CUTextFieldLongTextPreview() {
    AppTheme {
        CUTextField(
            modifier = Modifier.height(200.dp),
            hint = "This is a long hint, This is a long hint, This is a long hint, This is a long hint, This is a long hint, This is a long hint, This is a long hint, This is a long hint, This is a long hint, This is a long hint, This is a long hint, ",
            visualTransformation = VisualTransformation.None,
            value = "",
            onValueChanged = {},
            singleLine = false,
        )
    }
}
