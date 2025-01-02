package com.hulkdx.findprofessional.feature.pro.availability.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.hulkdx.findprofessional.core.ui.commonui.navbar.ProAppNavBarContainer
import com.hulkdx.findprofessional.feature.pro.availability.main.components.ProAvailabilityCalendarView
import kotlinx.datetime.LocalDate
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProAvailabilityScreen(viewModel: ProAvailabilityViewModel = koinViewModel()) {

    val availabilities by viewModel.availabilities.collectAsState()
    val error by viewModel.error.collectAsState()

    ProAvailabilityScreen(
        availabilities,
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) },
        onDateClicked = viewModel::onDateClicked,
    )
}

@Composable
fun ProAvailabilityScreen(
    availabilities: List<LocalDate>,
    error: String?,
    onErrorDismissed: () -> Unit,
    onDateClicked: (LocalDate) -> Unit,
) {
    ProAppNavBarContainer(
        modifier = Modifier.testTag("ProAvailabilityScreen"),
        error = error,
        onErrorDismissed = onErrorDismissed,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ProAvailabilityCalendarView(
                availabilities,
                onDateClicked,
            )
        }
    }
}
