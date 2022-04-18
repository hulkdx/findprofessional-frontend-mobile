package com.hulkdx.findprofessional.feature.authentication.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.theme.LightGrey
import com.hulkdx.findprofessional.core.theme.body2

@Composable
fun CommonTextField(
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
                contentDescription = leadingIconContentDescription
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
            backgroundColor = LightGrey,
        ),
        visualTransformation = visualTransformation,
    )
}
