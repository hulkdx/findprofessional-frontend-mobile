package com.hulkdx.findprofessional.feature.authentication.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hulkdx.findprofessional.common.resources.*
import com.hulkdx.findprofessional.core.commonui.CUTextField
import org.jetbrains.compose.resources.stringResource

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    CUTextField(
        modifier = modifier.fillMaxWidth(),
        hint = stringResource(Res.string.email),
        value = value,
        onValueChanged = onValueChanged,
    )
}
