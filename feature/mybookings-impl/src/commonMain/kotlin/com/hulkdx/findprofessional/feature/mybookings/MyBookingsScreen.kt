package com.hulkdx.findprofessional.feature.mybookings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.ui.commonui.navbar.AppNavBarContainer
import com.hulkdx.findprofessional.core.ui.commonui.navbar.AppNavigationBarDimens
import com.hulkdx.findprofessional.core.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MyBookingsScreen(
    viewModel: MyBookingsViewModel = koinViewModel(),
) {
    MyBookingsScreen(null, {})
}

@Composable
fun MyBookingsScreen(
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    AppNavBarContainer(
        modifier = Modifier.testTag("MyBookingsScreen"),
        error = error,
        onErrorDismissed = onErrorDismissed,
    ) {
        MyBookingsScreenContent()
    }
}

@Composable
fun MyBookingsScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .verticalScroll(rememberScrollState())
            .padding(bottom = AppNavigationBarDimens.Height.dp)
    ) {
    }
}

@Preview
@Composable
private fun MyBookingsScreenPreview() {
    AppTheme {
        MyBookingsScreen(
            error = null,
            onErrorDismissed = {},
        )
    }
}
