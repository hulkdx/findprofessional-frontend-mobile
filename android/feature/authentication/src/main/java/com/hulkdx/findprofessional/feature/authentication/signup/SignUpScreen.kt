package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.common.feature.authentication.signup.exception.EmailExistsException
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.feature.authentication.ui.EmailTextField
import com.hulkdx.findprofessional.feature.authentication.ui.FilledButton
import com.hulkdx.findprofessional.feature.authentication.ui.PasswordTextField
import org.koin.androidx.compose.getViewModel

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = getViewModel(),
) {
    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary),
            verticalArrangement = Arrangement.Center,
        ) {
            EmailTextField(
                modifier = Modifier
                    .statusBarsPadding(),
                value = email,
                onValueChanged = viewModel::setEmail,
            )
            PasswordTextField(
                modifier = Modifier
                    .padding(top = 8.dp),
                value = password,
                onValueChanged = viewModel::setPassword,
            )
            SubmitButton(
                modifier = Modifier
                    .padding(top = 16.dp),
                onClick = viewModel::onSubmitClicked
            )
        }
        SignUpError(
            modifier = Modifier.align(Alignment.BottomCenter),
            error = error,
        )
    }
}

@Composable
private fun SubmitButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FilledButton(
        modifier = modifier,
        text = stringResource(R.string.signUp),
        onClick = onClick,
    )
}

@Composable
private fun SignUpError(
    modifier: Modifier = Modifier,
    error: Throwable?,
) {
    if (error == null) {
        return
    }

    val hostState = remember { SnackbarHostState() }
    val msg = when (error) {
        is EmailExistsException -> stringResource(R.string.emailExists)
        else -> stringResource(R.string.generalError)
    }
    LaunchedEffect(error) {
        hostState.showSnackbar(msg)
    }
    SnackbarHost(
        hostState = hostState,
        modifier = modifier,
    )
}
