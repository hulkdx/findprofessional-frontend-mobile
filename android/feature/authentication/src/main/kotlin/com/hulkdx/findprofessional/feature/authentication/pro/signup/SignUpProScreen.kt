package com.hulkdx.findprofessional.feature.authentication.pro.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.commonui.CUTextField
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body3Medium
import com.hulkdx.findprofessional.core.theme.h1Medium
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(horizontal = 24.dp),
        ) {
            item {
                Header()
            }

            item {
                EmailTextField(
                    modifier = Modifier.padding(top = 8.dp),
                    value = email,
                    onValueChanged = onEmailChanged
                )
            }

            item {
                PasswordTextField(
                    modifier = Modifier.padding(top = 8.dp),
                    value = password,
                    onValueChanged = onPasswordChanged
                )
            }

            item {
                CUTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    hint = "Skype ID",
                    value = "",
                    onValueChanged = {},
                )
            }

            item {
                HeaderName()
            }

            item {
                FirstNameTextField(
                    modifier = Modifier.padding(top = 8.dp),
                    value = firstName,
                    onValueChanged = onFirstNameChanged
                )
            }

            item {
                LastNameTextField(
                    modifier = Modifier.padding(top = 8.dp),
                    value = lastName,
                    onValueChanged = onLastNameChanged
                )
            }

            item {
                HeaderPhoto()
            }

            item {
                AddProfilePictureContent(onClick = {})
            }

            item {
                CUTextField(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .fillMaxWidth()
                        .height(200.dp),
                    hint = "About Me",
                    value = "",
                    onValueChanged = {},
                    singleLine = false,
                )
            }
        }
        CUSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            message = error,
            onDismiss = onErrorDismissed,
        )
    }
}

@Composable
private fun Header() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 48.dp,
                bottom = 8.dp,
            ),
        text = "Coach Registration",
        style = h1Medium,
        textAlign = TextAlign.Center,
    )
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
private fun HeaderName() {
    Text(
        modifier = Modifier
            .padding(
                top = 48.dp,
                bottom = 8.dp,
            ),
        text = "Please ensure that the details you provide match those on your government-issued ID.",
        style = body3Medium,
    )
}

@Composable
private fun HeaderPhoto() {
    Text(
        modifier = Modifier
            .padding(
                top = 48.dp,
                bottom = 8.dp,
            ),
        text = "Your photo will be shown to the other users in coach page.\n\n* At least 250*250 pixels\n* JPG, PNG and BMP formats only\n* Maximum size of 2MB",
        style = body3Medium,
    )
}

@Composable
private fun AddProfilePictureContent(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.padding(top = 20.dp),
            painter = painterResource(R.drawable.ic_profile_circle),
            contentDescription = "",
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "Add profile picture",
            style = body3Medium,
        )
    }
}

@Composable
@Preview
private fun SignUpScreenPreview() {
    AppTheme {
        SignUpProScreen(
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
