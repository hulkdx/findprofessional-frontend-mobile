package com.hulkdx.findprofessional.core.commonui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.theme.body2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CUTextField(
    modifier: Modifier,
    hint: String,
    @DrawableRes leadingIconDrawable: Int,
    leadingIconContentDescription: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        singleLine = true,
        leadingIcon = {
            Image(
                painter = painterResource(leadingIconDrawable),
                contentDescription = leadingIconContentDescription,
            )
        },
        shape = RoundedCornerShape(8.dp),
        placeholder = {
            Text(
                text = hint,
                style = body2,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.onTertiary,
        ),
        visualTransformation = visualTransformation,
    )
}
