package com.hulkdx.findprofessional.feature.authentication.pro.signup

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.core.commonui.CUFilledButton
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.commonui.CUTextField
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpScreen
import com.hulkdx.findprofessional.feature.authentication.ui.EmailTextField
import com.hulkdx.findprofessional.feature.authentication.ui.PasswordTextField
import com.hulkdx.findprofessional.resources.MR
import dev.icerock.moko.resources.compose.localized
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpProScreen(
    viewModel: SignUpProViewModel = koinViewModel(),
) {
    val data by viewModel.uiState.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    SignUpProScreen(
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
fun SignUpProScreen(
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
            .systemBarsPadding()
            .imePadding()
            .testTag("SignUpProScreen"),
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
        hint = stringResource(id = MR.strings.firstName.resourceId),
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
        hint = stringResource(id = MR.strings.lastName.resourceId),
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
        text = stringResource(MR.strings.signUp.resourceId),
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