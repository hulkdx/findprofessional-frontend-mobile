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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.core.theme.body1
import com.hulkdx.findprofessional.core.utils.append
import com.hulkdx.findprofessional.core.utils.bold
import com.hulkdx.findprofessional.core.utils.singleClick
import com.hulkdx.findprofessional.feature.authentication.ui.AppSnackBar
import com.hulkdx.findprofessional.feature.authentication.ui.EmailTextField
import com.hulkdx.findprofessional.feature.authentication.ui.FilledButton
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

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary),
            verticalArrangement = Arrangement.Center
        ) {
            EmailTextField(
                modifier = Modifier.statusBarsPadding(),
                value = email,
                onValueChanged = viewModel::setEmail
            )

            PasswordTextField(
                modifier = Modifier.padding(top = 8.dp),
                value = password,
                onValueChanged = viewModel::setPassword
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
        AppSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            message = error?.localized(),
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
        text = stringResource(id = MR.strings.signIn.resourceId),
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
            onClick = singleClick(onClick),
            Modifier.testTag(stringResource(id = MR.strings.signUp.resourceId)),
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = buildAnnotatedString {
                    append(MR.strings.dontHaveAnAccount.resourceId)
                    append(" ")
                    bold { append(id = MR.strings.signUp.resourceId) }
                },
                style = body1,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
