package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.feature.authentication.login.LoginViewModel
import com.hulkdx.findprofessional.feature.authentication.ui.EmailTextField
import com.hulkdx.findprofessional.feature.authentication.ui.PasswordTextField
import org.koin.androidx.compose.getViewModel

@Composable
@Preview
fun SignUpScreen(
    viewModel: SignUpViewModel = getViewModel(),
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.onPrimary),
    ) {
        EmailTextField(
            modifier = Modifier.statusBarsPadding(),
            value = email,
            onValueChanged = { email = it }
        )
        PasswordTextField(
            value = password,
            onValueChanged = { password = it }
        )
        SubmitButton(onClick = viewModel::onSubmitClicked)
    }
}

@Composable
private fun SubmitButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
    ) {
        Text(text = stringResource(R.string.signUp))
    }
}
