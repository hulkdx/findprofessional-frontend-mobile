package com.hulkdx.findprofessional.feature.pro.auth.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.commonui.CUEmailTextField
import com.hulkdx.findprofessional.core.commonui.CUFilledButton
import com.hulkdx.findprofessional.core.commonui.CUPasswordTextField
import com.hulkdx.findprofessional.core.commonui.CUTextField
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.aboutMe
import com.hulkdx.findprofessional.core.resources.coachType
import com.hulkdx.findprofessional.core.resources.consentID
import com.hulkdx.findprofessional.core.resources.consentWebcam
import com.hulkdx.findprofessional.core.resources.currency
import com.hulkdx.findprofessional.core.resources.price
import com.hulkdx.findprofessional.core.resources.priceHeader
import com.hulkdx.findprofessional.core.resources.signUp
import com.hulkdx.findprofessional.core.resources.skypeId
import com.hulkdx.findprofessional.core.theme.body3Medium
import com.hulkdx.findprofessional.feature.pro.auth.signup.model.ProRegisterRequest
import org.jetbrains.compose.resources.stringResource

@Composable
fun SignUpProScreenStep2(
    uiState: ProRegisterRequest,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSkypeIdChanged: (String) -> Unit,
    onCoachTypeChanged: (String) -> Unit,
    onRegisterClicked: () -> Unit,
    onAboutMeChanged: (String) -> Unit,
    onPriceChanged: (String) -> Unit,
    onCurrencyChanged: (String) -> Unit,
    onWebcamConsentCheckedChange: ((Boolean) -> Unit)?,
    onIdConsentCheckedChange: ((Boolean) -> Unit)?,
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    SignUpProScreenLayout(
        error = error,
        onErrorDismissed = onErrorDismissed,

        { Email(uiState.email, onEmailChanged) },
        { Password(uiState.password, onPasswordChanged) },
        { SkypeID(uiState.skypeId, onSkypeIdChanged) },
        { CoachType(uiState.coachType, onCoachTypeChanged) },
        { AboutMe(uiState.aboutMe, onAboutMeChanged) },
        { PriceHeader() },
        { Price(uiState.price, onPriceChanged, uiState.priceCurrency, onCurrencyChanged) },
        { WebcamConsent(uiState.webcamConsentChecked, onWebcamConsentCheckedChange) },
        { IdConsent(uiState.idConsentChecked, onIdConsentCheckedChange) },
        { RegisterButton(onRegisterClicked) },
    )
}


@Composable
private fun Email(
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    CUEmailTextField(
        modifier = Modifier.padding(top = 8.dp),
        value = value,
        onValueChanged = onValueChanged,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
        ),
    )
}

@Composable
private fun Password(
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    CUPasswordTextField(
        modifier = Modifier.padding(top = 8.dp),
        value = value,
        onValueChanged = onValueChanged,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
        ),
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
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
        ),
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
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
        ),
    )
}

@Composable
private fun AboutMe(
    value: String,
    onValueChanged: (String) -> (Unit),
) {
    CUTextField(
        modifier = Modifier
            .padding(top = 8.dp)
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
        modifier = Modifier.padding(top = 8.dp).padding(8.dp),
        color = MaterialTheme.colorScheme.errorContainer,
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
                .fillMaxWidth(),
            hint = stringResource(Res.string.price),
            value = price,
            onValueChanged = onPriceChanged,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
        )
        CUTextField(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            hint = stringResource(Res.string.currency),
            value = priceCurrency,
            onValueChanged = onPriceCurrencyChanged,
            singleLine = true,
        )
    }
}

@Composable
fun WebcamConsent(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
) {
    Row(
        Modifier.padding(top = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(checked, onCheckedChange)
        Text(
            text = stringResource(Res.string.consentWebcam),
            color = MaterialTheme.colorScheme.errorContainer,
            style = body3Medium,
        )
    }
}

@Composable
fun IdConsent(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
) {
    Row(
        Modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(checked, onCheckedChange)
        Text(
            text = stringResource(Res.string.consentID),
            color = MaterialTheme.colorScheme.errorContainer,
            style = body3Medium,
        )
    }
}

@Composable
private fun RegisterButton(
    onClick: () -> Unit,
) {
    CUFilledButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        text = stringResource(Res.string.signUp),
        onClick = onClick,
    )
}
