package com.hulkdx.findprofessional.feature.authentication.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.common.config.isDebug
import com.hulkdx.findprofessional.common.resources.Res
import com.hulkdx.findprofessional.common.resources.dontHaveAnAccount
import com.hulkdx.findprofessional.common.resources.forgotYourPassword
import com.hulkdx.findprofessional.common.resources.signIn
import com.hulkdx.findprofessional.common.resources.signUp
import com.hulkdx.findprofessional.core.commonui.CUFilledButton
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.commonui.CUTextButton
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.utils.singleClick
import com.hulkdx.findprofessional.feature.authentication.ui.EmailTextField
import com.hulkdx.findprofessional.feature.authentication.ui.LogoImage
import com.hulkdx.findprofessional.feature.authentication.ui.PasswordTextField
import org.jetbrains.compose.resources.stringResource
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
) {
    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    val showDeveloper = isDebug()

    LoginScreen(
        email = email,
        onEmailChanged = viewModel::setEmail,
        password = password,
        onPasswordChanged = viewModel::setPassword,
        onSignInClicked = viewModel::onSignInClicked,
        onSignUpClicked = viewModel::onSignUpClicked,
        onForgotPasswordClicked = viewModel::onForgotPasswordClicked,
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) },
        showDeveloper = showDeveloper,
        onDevClicked = viewModel::onDevClicked,
    )
}

@Composable
fun LoginScreen(
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    onSignInClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
    onForgotPasswordClicked: () -> Unit,
    error: String?,
    onErrorDismissed: () -> Unit,
    showDeveloper: Boolean,
    onDevClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onPrimary)
            .systemBarsPadding()
            .imePadding()
            .testTag("LoginScreen")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {

            Spacer(modifier = Modifier.weight(2.5F))

            LogoImage(
                modifier = Modifier
                    .width(110.dp)
                    .align(CenterHorizontally)
            )

            EmailTextField(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .padding(horizontal = 24.dp),
                value = email,
                onValueChanged = onEmailChanged,
            )

            PasswordTextField(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 24.dp),
                value = password,
                onValueChanged = onPasswordChanged,
            )

            LoginButton(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 24.dp),
                onClick = onSignInClicked,
            )

            ForgotPasswordButton(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(CenterHorizontally),
                onClick = onForgotPasswordClicked,
            )

            Spacer(modifier = Modifier.weight(1F))

            SignUpButton(
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .align(CenterHorizontally),
                onClick = onSignUpClicked,
            )
        }
        CUSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            message = error,
            onDismiss = onErrorDismissed
        )

        if (showDeveloper) {
            DeveloperButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = onDevClicked,
            )
        }
    }
}

@Composable
fun LoginButton(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    CUFilledButton(
        modifier = modifier.fillMaxWidth(),
        text = stringResource(Res.string.signIn),
        onClick = onClick,
    )
}

@Composable
fun ForgotPasswordButton(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    CUTextButton(
        modifier = modifier,
        text = stringResource(Res.string.forgotYourPassword),
        onClick = singleClick(onClick),
    )
}

@Composable
fun SignUpButton(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    CUTextButton(
        modifier = modifier
            .testTag(stringResource(Res.string.signUp)),
        text = stringResource(Res.string.dontHaveAnAccount),
        onClick = singleClick(onClick),
    )
}

@Composable
fun DeveloperButton(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    TextButton(modifier = modifier, onClick = onClick) {
        Text(text = "dev")
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    AppTheme {
        LoginScreen(
            email = "",
            onEmailChanged = {},
            password = "",
            onPasswordChanged = {},
            onSignInClicked = {},
            onSignUpClicked = {},
            error = "",
            onErrorDismissed = {},
            onForgotPasswordClicked = {},
            onDevClicked = {},
            showDeveloper = true,
        )
    }
}
