package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
            onValueChanged = { viewModel.email.value = it },
        )
        PasswordTextField(
            modifier = Modifier
                .padding(top = 8.dp),
            value = password,
            onValueChanged = { viewModel.password.value = it },
        )
        SubmitButton(
            modifier = Modifier
                .padding(top = 16.dp),
            onClick = viewModel::onSubmitClicked
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
