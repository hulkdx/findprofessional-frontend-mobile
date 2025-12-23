package com.hulkdx.findprofessional.feature.pro.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.hulkdx.findprofessional.core.ui.commonui.navbar.ProAppNavBarContainer
import com.hulkdx.findprofessional.feature.pro.schedule.model.ScheduleUiState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProScheduleScreen(viewModel: ProScheduleViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val error by viewModel.error.collectAsState()

    ProScheduleScreen(
        state,
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) },
    )
}

@Composable
fun ProScheduleScreen(
    state: ScheduleUiState,
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    ProAppNavBarContainer(
        modifier = Modifier.testTag("ProScheduleScreen"),
        error = error,
        onErrorDismissed = onErrorDismissed,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // TODO:
        }
    }
}
