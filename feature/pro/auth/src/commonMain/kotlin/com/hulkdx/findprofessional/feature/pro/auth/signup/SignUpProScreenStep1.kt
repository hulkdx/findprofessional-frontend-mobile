package com.hulkdx.findprofessional.feature.pro.auth.signup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.commonui.CUFilledButton
import com.hulkdx.findprofessional.core.commonui.CUTextField
import com.hulkdx.findprofessional.core.model.proauth.SignUpProUiState
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.firstName
import com.hulkdx.findprofessional.core.resources.headerForNames
import com.hulkdx.findprofessional.core.resources.lastName
import com.hulkdx.findprofessional.core.resources.next
import com.hulkdx.findprofessional.core.theme.body3Medium
import org.jetbrains.compose.resources.stringResource

@Composable
fun SignUpProScreenStep1(
    uiState: SignUpProUiState,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onNextClicked: () -> Unit,
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    SignUpProScreenLayout(
        error = error,
        onErrorDismissed = onErrorDismissed,

        { FirstName(uiState.firstName, onFirstNameChanged) },
        { LastName(uiState.lastName, onLastNameChanged) },
        { NameHeader() },
        { NextButton(onNextClicked) },
    )
}

@Composable
private fun FirstName(
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    CUTextField(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        hint = stringResource(Res.string.firstName),
        value = value,
        onValueChanged = onValueChanged,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
        ),
    )
}

@Composable
private fun LastName(
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    CUTextField(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        hint = stringResource(Res.string.lastName),
        value = value,
        onValueChanged = onValueChanged,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
        ),
    )
}

@Composable
private fun NameHeader() {
    Text(
        modifier = Modifier
            .padding(top = 8.dp)
            .padding(8.dp),
        text = stringResource(Res.string.headerForNames),
        color = MaterialTheme.colorScheme.errorContainer,
        style = body3Medium,
    )
}

@Composable
private fun NextButton(
    onClick: () -> Unit,
) {
    CUFilledButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        text = stringResource(Res.string.next),
        onClick = onClick,
    )
}
