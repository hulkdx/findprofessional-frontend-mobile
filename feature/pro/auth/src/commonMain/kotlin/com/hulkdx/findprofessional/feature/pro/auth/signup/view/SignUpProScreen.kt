package com.hulkdx.findprofessional.feature.pro.auth.signup.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.commonui.BACK_BUTTON_HEIGHT
import com.hulkdx.findprofessional.core.commonui.CUBackButton
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.model.proauth.SignUpProRequest
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.feature.pro.auth.signup.SignUpProViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun SignUpProScreen(
    initialUiState: SignUpProRequest,
    viewModel: SignUpProViewModel = koinViewModel { parametersOf(initialUiState) },
) {
    val uiState by viewModel.uiState.collectAsState()
    val error by viewModel.error.collectAsState()

    SignUpProScreen(
        uiState = uiState,
        onFirstNameChanged = viewModel::setFirstName,
        onLastNameChanged = viewModel::setLastName,
        onEmailChanged = viewModel::setEmail,
        onPasswordChanged = viewModel::setPassword,
        onSkypeIdChanged = viewModel::setSkypeId,
        onCoachTypeChanged = viewModel::setCoachType,
        onAboutMeChanged = viewModel::setAboutMe,
        onRegisterClicked = viewModel::onRegisterClicked,
        onPriceChanged = viewModel::setPrice,
        onCurrencyChanged = viewModel::setCurrency,
        onWebcamConsentCheckedChange = viewModel::setWebcamConsent,
        onIdConsentCheckedChange = viewModel::setIdConsent,
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) }
    )
}

@Composable
fun SignUpProScreen(
    uiState: SignUpProRequest,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSkypeIdChanged: (String) -> Unit,
    onCoachTypeChanged: (String) -> Unit,
    onAboutMeChanged: (String) -> Unit,
    onPriceChanged: (String) -> Unit,
    onCurrencyChanged: (String) -> Unit,
    onRegisterClicked: () -> Unit,
    onWebcamConsentCheckedChange: ((Boolean) -> Unit)?,
    onIdConsentCheckedChange: ((Boolean) -> Unit)?,
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    val navigator: Navigator = koinInject()

    when (uiState.step) {
        1 -> SignUpProScreenStep1(
            uiState,
            onFirstNameChanged,
            onLastNameChanged,
            onNextClicked = { navigator.navigate(NavigationScreen.ProSignUp(uiState.copy(step = 2))) },
            error,
            onErrorDismissed,
        )

        2 -> SignUpProScreenStep2(
            uiState,
            onEmailChanged,
            onPasswordChanged,
            onSkypeIdChanged,
            onCoachTypeChanged,
            onRegisterClicked,
            onAboutMeChanged,
            onPriceChanged,
            onCurrencyChanged,
            onWebcamConsentCheckedChange,
            onIdConsentCheckedChange,
            error,
            onErrorDismissed,
        )
    }
}

@Composable
fun SignUpProScreenLayout(
    modifier: Modifier = Modifier,
    error: String?,
    onErrorDismissed: () -> Unit,
    vararg contents: @Composable LazyItemScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.onPrimary)
            .fillMaxSize()
            .systemBarsPadding()
            .imePadding(),
    ) {
        LazyColumn(
            modifier = Modifier
                .testTag("SignUpProScreen.LazyColumn")
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = BACK_BUTTON_HEIGHT.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            contents.forEach { content ->
                item(content = content)
            }
        }
        CUSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            message = error,
            onDismiss = onErrorDismissed,
        )
        CUBackButton(modifier = Modifier.align(Alignment.TopStart))
    }
}
