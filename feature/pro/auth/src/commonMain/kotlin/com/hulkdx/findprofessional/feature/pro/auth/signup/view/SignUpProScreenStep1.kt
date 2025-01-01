package com.hulkdx.findprofessional.feature.pro.auth.signup.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.features.proauth.SignUpProRequest
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.firstName
import com.hulkdx.findprofessional.core.resources.headerForNames
import com.hulkdx.findprofessional.core.resources.lastName
import com.hulkdx.findprofessional.core.resources.next
import com.hulkdx.findprofessional.core.ui.commonui.CUFilledButton
import com.hulkdx.findprofessional.core.ui.commonui.CUTextField
import com.hulkdx.findprofessional.core.ui.theme.body3Medium
import com.hulkdx.findprofessional.core.utils.singleClick
import org.jetbrains.compose.resources.stringResource

@Composable
fun SignUpProScreenStep1(
    uiState: SignUpProRequest,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onNextClicked: () -> Unit,
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    SignUpProScreenLayout(
        modifier = Modifier.testTag("SignUpProScreen"),
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
        onClick = singleClick(onClick),
    )
}
