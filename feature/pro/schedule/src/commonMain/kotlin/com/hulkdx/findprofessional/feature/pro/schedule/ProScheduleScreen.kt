package com.hulkdx.findprofessional.feature.pro.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.hulkdx.findprofessional.core.commonui.navbar.AppNavBarContainer
import com.hulkdx.findprofessional.core.commonui.navbar.ProAppNavBarContainer
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProScheduleScreen(viewModel: ProScheduleViewModel = koinViewModel()) {
    val error by viewModel.error.collectAsState()

    ProScheduleScreen(
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) },
    )
}

@Composable
fun ProScheduleScreen(
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
