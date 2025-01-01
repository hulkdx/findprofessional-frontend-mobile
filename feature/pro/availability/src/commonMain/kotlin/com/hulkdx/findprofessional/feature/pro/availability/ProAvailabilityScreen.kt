package com.hulkdx.findprofessional.feature.pro.availability

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.hulkdx.findprofessional.core.ui.commonui.navbar.ProAppNavBarContainer
import com.hulkdx.findprofessional.feature.pro.availability.components.ProAvailabilityCalendarView
import kotlinx.datetime.LocalDate
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProAvailabilityScreen(viewModel: ProAvailabilityViewModel = koinViewModel()) {
    val error by viewModel.error.collectAsState()

    ProAvailabilityScreen(
        error = error?.localized(),
        onErrorDismissed = viewModel::onErrorDismissed,
        onDateClicked = viewModel::onDateClicked,
        isSelectedDay = viewModel::isAvailableDay
    )
}

@Composable
fun ProAvailabilityScreen(
    error: String?,
    onErrorDismissed: () -> Unit,
    onDateClicked: (LocalDate) -> Unit,
    isSelectedDay: (Int) -> Boolean,
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
                onDateClicked,
                isSelectedDay,
            )
        }
    }
}
