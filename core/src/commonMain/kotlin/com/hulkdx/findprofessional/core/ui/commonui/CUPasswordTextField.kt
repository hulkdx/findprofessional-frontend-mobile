package com.hulkdx.findprofessional.core.ui.commonui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.password
import org.jetbrains.compose.resources.stringResource

@Composable
fun CUPasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChanged: (String) -> (Unit),
) {
    CUTextField(
        modifier = modifier.fillMaxWidth(),
        hint = stringResource(Res.string.password),
        visualTransformation = PasswordVisualTransformation(),
        value = value,
        onValueChanged = onValueChanged,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    )
}
