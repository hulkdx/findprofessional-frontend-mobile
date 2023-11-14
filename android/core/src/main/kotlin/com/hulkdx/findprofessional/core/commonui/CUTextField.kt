package com.hulkdx.findprofessional.core.commonui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.core.theme.body2Medium

@Composable
fun CUTextField(
    modifier: Modifier,
    hint: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        placeholder = {
            Text(
                text = hint,
                style = body2Medium,
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.onTertiary,
            unfocusedContainerColor = MaterialTheme.colorScheme.onTertiary,
            disabledContainerColor = MaterialTheme.colorScheme.onTertiary,
        ),
        visualTransformation = visualTransformation,
    )
}

@Preview
@Composable
private fun CUTextField() {
    CUTextField(
        modifier = Modifier,
        hint = "Hint",
        visualTransformation = VisualTransformation.None,
        value = "",
        onValueChanged = {}
    )
}
