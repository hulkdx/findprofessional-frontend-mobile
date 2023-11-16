package com.hulkdx.findprofessional.feature.authentication.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.common.config.isDebug
import com.hulkdx.findprofessional.core.commonui.CUFilledButton
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.commonui.CUTextButton
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.utils.singleClick
import com.hulkdx.findprofessional.feature.authentication.ui.EmailTextField
import com.hulkdx.findprofessional.feature.authentication.ui.LogoImage
import com.hulkdx.findprofessional.feature.authentication.ui.PasswordTextField
import com.hulkdx.findprofessional.resources.MR
import dev.icerock.moko.resources.compose.localized
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = getViewModel(),
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
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) },
        showDeveloper = showDeveloper,
        onDevClicked = viewModel::onDevClicked,
    )
}

@Composable
private fun LoginScreen(
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    onSignInClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
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
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .systemBarsPadding()
                .verticalScroll(rememberScrollState())
                .imePadding()
                .testTag("LoginScreen")
        ) {
            val (
                logoImage,
                emailConstraint,
                passwordConstraint,
                loginConstraint,
                forgotPasswordConstraint,
                signUpConstraint,
            ) = createRefs()

            createVerticalChain(
                logoImage,
                emailConstraint,
                passwordConstraint,
                loginConstraint,
                forgotPasswordConstraint,
                chainStyle = ChainStyle.Packed
            )

            LogoImage(
                modifier = Modifier
                    .constrainAs(logoImage) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .width(110.dp)
            )
            EmailTextField(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 50.dp)
                    .constrainAs(emailConstraint) {
                    },
                value = email,
                onValueChanged = onEmailChanged,
            )

            PasswordTextField(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 16.dp)
                    .constrainAs(passwordConstraint) {
                    },
                value = password,
                onValueChanged = onPasswordChanged,
            )

            LoginButton(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 16.dp)
                    .constrainAs(loginConstraint) {
                    },
                onClick = onSignInClicked,
            )

            ForgotPasswordButton(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .constrainAs(forgotPasswordConstraint) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                onClick = onSignInClicked,
            )

            SignUpButton(
                modifier = Modifier
                    .constrainAs(signUpConstraint) {
                        linkTo(forgotPasswordConstraint.bottom, parent.bottom, bias = .80F)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
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
        modifier.fillMaxWidth(),
        text = stringResource(id = MR.strings.signIn.resourceId),
        onClick,
    )
}

@Composable
fun ForgotPasswordButton(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    CUTextButton(
        modifier = modifier,
        text = stringResource(id = MR.strings.forgotYourPassword.resourceId),
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
            .testTag(stringResource(id = MR.strings.signUp.resourceId))
        ,
        text = stringResource(id = MR.strings.dontHaveAnAccount.resourceId),
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
            onDevClicked = {},
            showDeveloper = true,
        )
    }
}
