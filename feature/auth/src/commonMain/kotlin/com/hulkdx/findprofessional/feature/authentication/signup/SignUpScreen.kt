package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.commonui.CUBackButton
import com.hulkdx.findprofessional.core.commonui.CUFilledButton
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.commonui.CUTextField
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.firstName
import com.hulkdx.findprofessional.core.resources.lastName
import com.hulkdx.findprofessional.core.resources.signUp
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.feature.authentication.ui.EmailTextField
import com.hulkdx.findprofessional.feature.authentication.ui.PasswordTextField
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = koinViewModel(),
) {
    val data by viewModel.uiState.collectAsState()
    val error by viewModel.error.collectAsState()

    SignUpScreen(
        firstName = data.firstName,
        onFirstNameChanged = viewModel::setFirstName,
        lastName = data.lastName,
        onLastNameChanged = viewModel::setLastName,
        email = data.email,
        onEmailChanged = viewModel::setEmail,
        password = data.password,
        onPasswordChanged = viewModel::setPassword,
        onSubmitClicked = viewModel::onSubmitClicked,
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) }
    )
}

@Composable
fun SignUpScreen(
    firstName: String,
    onFirstNameChanged: (String) -> Unit,
    lastName: String,
    onLastNameChanged: (String) -> Unit,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    onSubmitClicked: () -> Unit,
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onPrimary)
            .systemBarsPadding()
            .imePadding()
            .testTag("SignUpScreen"),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            FirstNameTextField(
                value = firstName,
                onValueChanged = onFirstNameChanged
            )

            LastNameTextField(
                modifier = Modifier.padding(top = 8.dp),
                value = lastName,
                onValueChanged = onLastNameChanged
            )

            EmailTextField(
                modifier = Modifier.padding(top = 8.dp),
                value = email,
                onValueChanged = onEmailChanged
            )

            PasswordTextField(
                modifier = Modifier.padding(top = 8.dp),
                value = password,
                onValueChanged = onPasswordChanged
            )

            SubmitButton(
                modifier = Modifier.padding(top = 16.dp),
                onClick = onSubmitClicked,
            )
        }
        CUBackButton(modifier = Modifier.align(Alignment.TopStart))
        CUSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            message = error,
            onDismiss = onErrorDismissed,
        )
    }
}


@Composable
private fun FirstNameTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    CUTextField(
        modifier = modifier.fillMaxWidth(),
        hint = stringResource(Res.string.firstName),
        value = value,
        onValueChanged = onValueChanged,
    )
}

@Composable
private fun LastNameTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    CUTextField(
        modifier = modifier.fillMaxWidth(),
        hint = stringResource(Res.string.lastName),
        value = value,
        onValueChanged = onValueChanged,
    )
}

@Composable
private fun SubmitButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    CUFilledButton(
        modifier = modifier.fillMaxWidth(),
        text = stringResource(Res.string.signUp),
        onClick = onClick,
    )
}

@Composable
@Preview
private fun SignUpScreenPreview() {
    AppTheme {
        SignUpScreen(
            firstName = "",
            onFirstNameChanged = {},
            lastName = "",
            onLastNameChanged = {},
            email = "",
            onEmailChanged = {},
            password = "",
            onPasswordChanged = {},
            onSubmitClicked = {},
            error = "",
            onErrorDismissed = {}
        )
    }
}
