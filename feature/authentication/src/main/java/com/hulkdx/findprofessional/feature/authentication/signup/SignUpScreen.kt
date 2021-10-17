package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hulkdx.findprofessional.R

@Composable
@Preview
fun SignUpScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize()) {
        EmailTextField(value = email, onValueChanged = { email = it })
        PasswordTextField(value = password, onValueChanged = { password = it })
        SubmitButton(onClick = {})
    }
}

@Composable
private fun EmailTextField(
    value: String,
    onValueChanged: (String) -> Unit,
) {
    TextField(
        value = value,
        label = {
            Text(text = stringResource(R.string.email))
        },
        onValueChange = onValueChanged,
    )
}

@Composable
private fun PasswordTextField(
    value: String,
    onValueChanged: (String) -> Unit,
) {
    TextField(
        value = value,
        label = {
            Text(text = stringResource(R.string.password))
        },
        onValueChange = onValueChanged,
    )
}

@Composable
private fun SubmitButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
    ) {
        Text(text = stringResource(R.string.signUp))
    }
}
