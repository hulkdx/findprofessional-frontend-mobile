package com.hulkdx.findprofessional.feature.pro.auth.signup

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
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.commonui.CUBackButton
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.feature.pro.auth.signup.model.ProRegisterRequest
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun SignUpProScreen(
    step: Int,
    viewModel: SignUpProViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val error by viewModel.error.collectAsState()

    SignUpProScreen(
        step = step,
        uiState = uiState,
        onFirstNameChanged = viewModel::setFirstName,
        onLastNameChanged = viewModel::setLastName,
        onEmailChanged = viewModel::setEmail,
        onPasswordChanged = viewModel::setPassword,
        onSkypeIdChanged = viewModel::setSkypeId,
        onCoachTypeChanged = viewModel::setCoachType,
        onAboutMeChanged = viewModel::setAboutMe,
        onRegisterClicked = viewModel::onRegisterClicked,
        onPriceChanged = viewModel::onPriceChanged,
        onCurrencyChanged = viewModel::onCurrencyChanged,
        onWebcamConsentCheckedChange = viewModel::onWebcamConsentCheckedChange,
        onIdConsentCheckedChange = viewModel::onIdConsentCheckedChange,
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) }
    )
}

@Composable
fun SignUpProScreen(
    step: Int,
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
    onRegisterClicked: () -> Unit,
    onWebcamConsentCheckedChange: ((Boolean) -> Unit)?,
    onIdConsentCheckedChange: ((Boolean) -> Unit)?,
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    val navigator: Navigator = koinInject()

    when (step) {
        1 -> SignUpProScreenStep1(
            uiState,
            onFirstNameChanged,
            onLastNameChanged,
            onNextClicked = { navigator.navigate(NavigationScreen.SignUpPro(step = 2)) },
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
    error: String?,
    onErrorDismissed: () -> Unit,
    vararg contents: @Composable LazyItemScope.() -> Unit,
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onPrimary)
            .fillMaxSize()
            .systemBarsPadding()
            .imePadding(),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
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
