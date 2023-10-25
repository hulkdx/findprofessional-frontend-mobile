package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.feature.authentication.ui.EmailTextField
import com.hulkdx.findprofessional.feature.authentication.ui.FilledButton
import com.hulkdx.findprofessional.feature.authentication.ui.PasswordTextField
import com.hulkdx.findprofessional.resources.MR
import dev.icerock.moko.resources.compose.localized
import org.koin.androidx.compose.getViewModel

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = getViewModel(),
) {
    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    SignUpScreen(
        email = email,
        onEmailChanged = viewModel::setEmail,
        password = password,
        onPasswordChanged = viewModel::setPassword,
        onSubmitClicked = viewModel::onSubmitClicked,
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) }
    )
}

@Composable
private fun SignUpScreen(
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
            .testTag("SignUpScreen"),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.onPrimary),
            verticalArrangement = Arrangement.Center,
        ) {
            EmailTextField(
                modifier = Modifier,
                value = email,
                onValueChanged = onEmailChanged,
            )
            PasswordTextField(
                modifier = Modifier
                    .padding(top = 8.dp),
                value = password,
                onValueChanged = onPasswordChanged,
            )
            SubmitButton(
                modifier = Modifier
                    .padding(top = 16.dp),
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
@Preview
private fun SignUpScreenPreview() {
    AppTheme {
        SignUpScreen(
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

@Composable
private fun SubmitButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    FilledButton(
        modifier = modifier,
        text = stringResource(MR.strings.signUp.resourceId),
        onClick = onClick,
    )
}
