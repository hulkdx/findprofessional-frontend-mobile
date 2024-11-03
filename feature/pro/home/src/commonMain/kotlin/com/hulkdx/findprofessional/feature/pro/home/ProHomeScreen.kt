package com.hulkdx.findprofessional.feature.pro.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.hulkdx.findprofessional.feature.pro.home.tmp.EditAvailableTimeScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProHomeScreen(viewModel: ProHomeViewModel = koinViewModel()) {
    val error by viewModel.error.collectAsState()

    ProHomeScreen(
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) },
    )
}

@Composable
fun ProHomeScreen(
    error: String?,
    onErrorDismissed: () -> Unit,
) {
//    Text(
//        modifier = Modifier.testTag("ProHomeScreen"),
//        text = "ProHomeScreen",
//    )
    EditAvailableTimeScreen()
}
