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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.common.feature.authentication.pro.signup.model.ProRegisterRequest
import com.hulkdx.findprofessional.core.commonui.CUFilledButton
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.commonui.CUTextField
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.aboutMe
import com.hulkdx.findprofessional.core.resources.addProfilePicture
import com.hulkdx.findprofessional.core.resources.coachRegistration
import com.hulkdx.findprofessional.core.resources.coachType
import com.hulkdx.findprofessional.core.resources.currency
import com.hulkdx.findprofessional.core.resources.firstName
import com.hulkdx.findprofessional.core.resources.headerForNames
import com.hulkdx.findprofessional.core.resources.headerForPhotos
import com.hulkdx.findprofessional.core.resources.ic_profile_circle
import com.hulkdx.findprofessional.core.resources.lastName
import com.hulkdx.findprofessional.core.resources.price
import com.hulkdx.findprofessional.core.resources.priceHeader
import com.hulkdx.findprofessional.core.resources.signUp
import com.hulkdx.findprofessional.core.resources.skypeId
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body3Medium
import com.hulkdx.findprofessional.core.theme.h1Medium
import com.hulkdx.findprofessional.feature.authentication.ui.EmailTextField
import com.hulkdx.findprofessional.feature.authentication.ui.PasswordTextField
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpProScreen(
    viewModel: SignUpProViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    SignUpProScreen(
        uiState = uiState,
        onFirstNameChanged = viewModel::setFirstName,
        onLastNameChanged = viewModel::setLastName,
        onEmailChanged = viewModel::setEmail,
        onPasswordChanged = viewModel::setPassword,
        onSkypeIdChanged = viewModel::setSkypeId,
        onCoachTypeChanged = viewModel::setCoachType,
        onAboutMeChanged = viewModel::setAboutMe,
        onSubmitClicked = viewModel::onSubmitClicked,
        onPriceChanged = viewModel::onPriceChanged,
        onCurrencyChanged = viewModel::onCurrencyChanged,
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) }
    )
}

@Composable
fun SignUpProScreen(
    uiState: ProRegisterRequest,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSkypeIdChanged: (String) -> Unit,
    onCoachTypeChanged: (String) -> Unit,
    onAboutMeChanged: (String) -> Unit,
    onPriceChanged: (String) -> Unit,
    onCurrencyChanged: (String) -> Unit,
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
            item { Email(uiState.email, onEmailChanged) }
            item { Password(uiState.password, onPasswordChanged) }
            item { SkypeID(uiState.skypeId, onSkypeIdChanged) }
            item { CoachType(uiState.coachType, onCoachTypeChanged) }

            item { NameHeader() }
            item { FirstName(uiState.firstName, onFirstNameChanged) }
            item { LastName(uiState.lastName, onLastNameChanged) }

            item { PhotoHeader() }
            // TODO:
            // item { AddProfilePictureContent(onClick = {}) }
            item { AboutMe(uiState.aboutMe, onAboutMeChanged) }

            item { PriceHeader() }
            item { Price(uiState.price, onPriceChanged, uiState.priceCurrency, onCurrencyChanged) }

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
private fun CoachType(
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    CUTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        hint = stringResource(Res.string.coachType),
        value = value,
        onValueChanged = onValueChanged,
    )
}

@Composable
private fun NameHeader() {
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
private fun PhotoHeader() {
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
            painter = painterResource(Res.drawable.ic_profile_circle),
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
            .height(160.dp),
        hint = stringResource(Res.string.aboutMe),
        value = value,
        onValueChanged = onValueChanged,
        singleLine = false,
    )
}

@Composable
fun PriceHeader() {
    Text(
        modifier = Modifier
            .padding(
                top = 48.dp,
                bottom = 8.dp,
            ),
        text = stringResource(Res.string.priceHeader),
        style = body3Medium,
    )
}

@Composable
fun Price(
    price: String,
    onPriceChanged: (String) -> (Unit),
    priceCurrency: String,
    onPriceCurrencyChanged: (String) -> (Unit),
) {
    Column {
        CUTextField(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(),
            hint = stringResource(Res.string.price),
            value = price,
            onValueChanged = onPriceChanged,
            singleLine = false,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        )
        CUTextField(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            hint = stringResource(Res.string.currency),
            value = priceCurrency,
            onValueChanged = onPriceCurrencyChanged,
            singleLine = false,
            enabled = false,
        )
    }
}

@Composable
private fun SubmitButton(
    onClick: () -> Unit,
) {
    CUFilledButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        text = stringResource(Res.string.signUp),
        onClick = onClick,
    )
}

@Composable
@Preview(/*heightDp = 1200*/)
private fun SignUpScreenPreview() {
    AppTheme {
        SignUpProScreen(
            uiState = ProRegisterRequest(),
            error = null,
            onFirstNameChanged = {},
            onLastNameChanged = {},
            onEmailChanged = {},
            onPasswordChanged = {},
            onSubmitClicked = {},
            onErrorDismissed = {},
            onSkypeIdChanged = {},
            onAboutMeChanged = {},
            onCoachTypeChanged = {},
            onPriceChanged = {},
            onCurrencyChanged = {},
        )
    }
}
