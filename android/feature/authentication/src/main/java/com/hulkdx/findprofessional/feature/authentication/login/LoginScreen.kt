package com.hulkdx.findprofessional.feature.authentication.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.theme.body1
import com.hulkdx.findprofessional.core.utils.bold
import com.hulkdx.findprofessional.feature.authentication.R
import com.hulkdx.findprofessional.feature.authentication.ui.EmailTextField
import com.hulkdx.findprofessional.feature.authentication.ui.FilledButton
import com.hulkdx.findprofessional.feature.authentication.ui.PasswordTextField
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = getViewModel(),
) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary),
        verticalArrangement = Arrangement.Center
    ) {
        EmailTextField(
            modifier = Modifier.statusBarsPadding(),
            value = username.value,
            onValueChanged = { username.value = it }
        )

        PasswordTextField(
            modifier = Modifier.padding(top = 8.dp),
            value = password.value,
            onValueChanged = { password.value = it }
        )

        SignInButton(
            modifier = Modifier.padding(top = 16.dp),
            onClick = viewModel::onSignInClicked
        )

        SignUpButton(
            modifier = Modifier.padding(top = 32.dp),
            onClick = viewModel::onSignUpClicked
        )
    }
}

@Composable
private fun SignInButton(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    FilledButton(
        modifier,
        text = stringResource(id = R.string.signIn),
        onClick,
    )
}

@Composable
private fun SignUpButton(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter,
    ) {
        TextButton(
            onClick = onClick,
            Modifier.testTag(stringResource(id = R.string.signUp))
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.dontHaveAnAccount))
                    append(" ")
                    val bold = stringResource(id = R.string.signUp)
                    bold {
                        append(bold)
                    }
                },
                style = body1,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
