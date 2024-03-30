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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.common.resources.Res
import com.hulkdx.findprofessional.common.resources.aboutMe
import com.hulkdx.findprofessional.common.resources.addProfilePicture
import com.hulkdx.findprofessional.common.resources.coachRegistration
import com.hulkdx.findprofessional.common.resources.firstName
import com.hulkdx.findprofessional.common.resources.headerForNames
import com.hulkdx.findprofessional.common.resources.headerForPhotos
import com.hulkdx.findprofessional.common.resources.lastName
import com.hulkdx.findprofessional.common.resources.signUp
import com.hulkdx.findprofessional.common.resources.skypeId
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.core.commonui.CUFilledButton
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.commonui.CUTextField
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body3Medium
import com.hulkdx.findprofessional.core.theme.h1Medium
import com.hulkdx.findprofessional.feature.authentication.ui.EmailTextField
import com.hulkdx.findprofessional.feature.authentication.ui.PasswordTextField
import org.jetbrains.compose.resources.stringResource
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
        skypeId = data.skypeId,
        onSkypeIdChanged = viewModel::setSkypeId,
        aboutMe = data.aboutMe,
        onAboutMeChanged = viewModel::setAboutMe,
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
    skypeId: String,
    onSkypeIdChanged: (String) -> Unit,
    aboutMe: String,
    onAboutMeChanged: (String) -> Unit,
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
            item { Header() }
            item { Email(email, onEmailChanged) }
            item { Password(password, onPasswordChanged) }
            item { SkypeID(skypeId, onSkypeIdChanged) }

            item { HeaderForNames() }
            item { FirstName(firstName, onFirstNameChanged) }
            item { LastName(lastName, onLastNameChanged) }

            item { HeaderForPhotos() }
            // TODO:
            // item { AddProfilePictureContent(onClick = {}) }
            item { AboutMe(aboutMe, onAboutMeChanged) }
            item { SubmitButton(onSubmitClicked) }
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
        text = stringResource(Res.string.coachRegistration),
        style = h1Medium,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun Email(
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    EmailTextField(
        modifier = Modifier.padding(top = 8.dp), value = value, onValueChanged = onValueChanged
    )
}

@Composable
private fun Password(
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    PasswordTextField(
        modifier = Modifier.padding(top = 8.dp), value = value, onValueChanged = onValueChanged
    )
}

@Composable
private fun SkypeID(
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    CUTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        hint = stringResource(Res.string.skypeId),
        value = value,
        onValueChanged = onValueChanged,
    )
}

@Composable
private fun FirstName(
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    CUTextField(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        hint = stringResource(Res.string.firstName),
        value = value,
        onValueChanged = onValueChanged,
    )
}

@Composable
private fun LastName(
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    CUTextField(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        hint = stringResource(Res.string.lastName),
        value = value,
        onValueChanged = onValueChanged,
    )
}

@Composable
private fun HeaderForNames() {
    Text(
        modifier = Modifier
            .padding(
                top = 48.dp,
                bottom = 8.dp,
            ),
        text = stringResource(Res.string.headerForNames),
        style = body3Medium,
    )
}

@Composable
private fun HeaderForPhotos() {
    Text(
        modifier = Modifier
            .padding(
                top = 48.dp,
                bottom = 8.dp,
            ),
        text = stringResource(Res.string.headerForPhotos),
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
            text = stringResource(Res.string.addProfilePicture),
            style = body3Medium,
        )
    }
}

@Composable
private fun AboutMe(
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    CUTextField(
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxWidth()
            .height(200.dp),
        hint = stringResource(Res.string.aboutMe),
        value = value,
        onValueChanged = onValueChanged,
        singleLine = false,
    )
}

@Composable
private fun SubmitButton(
    onClick: () -> Unit,
) {
    CUFilledButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(Res.string.signUp),
        onClick = onClick,
    )
}

@Composable
@Preview(heightDp = 1200)
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
            onErrorDismissed = {},
            skypeId = "",
            onSkypeIdChanged = {},
            aboutMe = "",
            onAboutMeChanged = {},
        )
    }
}
