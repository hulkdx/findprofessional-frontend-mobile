package com.hulkdx.findprofessional.feature.home.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.feature.navigation.AppNavigationBar
import dev.icerock.moko.resources.compose.localized
import org.koin.androidx.compose.getViewModel


@Composable
fun HomeDetailScreen(viewModel: HomeDetailViewModel = getViewModel()) {
    val error by viewModel.error.collectAsStateWithLifecycle()

    HomeDetailScreen(
        error = error?.localized(),
        onErrorDismissed = { viewModel.error.set(null) },
    )
}

@Composable
private fun HomeDetailScreen(
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onTertiary)
            .systemBarsPadding()
            .testTag("HomeDetailScreen")
    ) {
        CUSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            message = error,
            onDismiss = onErrorDismissed
        )
        AppNavigationBar()
    }
}

@Preview
@Composable
private fun HomeDetailScreenPreview() {
    AppTheme {
        HomeDetailScreen(
            error = "",
            onErrorDismissed = {},
        )
    }
}
