package com.hulkdx.findprofessional.feature.pro.availability.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.hulkdx.findprofessional.core.ui.commonui.navbar.ProAppNavBarContainer
import com.hulkdx.findprofessional.core.utils.ClockProvider
import com.hulkdx.findprofessional.core.utils.now
import com.hulkdx.findprofessional.feature.pro.availability.main.components.ProAvailabilityCalendarView
import kotlinx.datetime.LocalDate
import org.koin.compose.viewmodel.koinViewModel
import org.koin.compose.koinInject

@Composable
fun ProAvailabilityScreen(viewModel: ProAvailabilityViewModel = koinViewModel()) {
    val availabilities by viewModel.availabilities.collectAsState()
    val error by viewModel.error.collectAsState()
    val clockProvider = koinInject<ClockProvider>()

    ProAvailabilityScreen(
        availabilities,
        now = LocalDate.now(clockProvider),
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) },
        onDateClicked = viewModel::onDateClicked,
    )
}

@Composable
fun ProAvailabilityScreen(
    availabilities: List<LocalDate>,
    now: LocalDate = LocalDate.now(),
    error: String?,
    onErrorDismissed: () -> Unit,
    onDateClicked: (LocalDate) -> Unit,
) {
    ProAppNavBarContainer(
        modifier = Modifier
            .fillMaxSize()
            .testTag("ProAvailabilityScreen"),
        error = error,
        onErrorDismissed = onErrorDismissed,
        hasStatusBarPadding = false,
    ) {
        ProAvailabilityCalendarView(
            now,
            availabilities,
            onDateClicked,
        )
    }
}
