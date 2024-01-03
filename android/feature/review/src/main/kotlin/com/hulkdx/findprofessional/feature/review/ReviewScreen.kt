package com.hulkdx.findprofessional.feature.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalReview
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.theme.AppTheme
import dev.icerock.moko.resources.compose.localized
import org.koin.androidx.compose.koinViewModel


@Composable
fun ReviewScreen(viewModel: ReviewViewModel = koinViewModel()) {
    val error by viewModel.error.collectAsStateWithLifecycle()
    val reviews by viewModel.reviews.collectAsStateWithLifecycle()

    ReviewScreen(
        reviews = reviews,
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) },
    )
}

@Composable
fun ReviewScreen(
    reviews: List<ProfessionalReview>,
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onTertiary)
            .fillMaxSize()
            .systemBarsPadding()
            .testTag("ReviewScreen")
    ) {
        CUSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            message = error,
            onDismiss = onErrorDismissed,
        )
    }
}

@Preview
@Composable
private fun ReviewScreenPreview() {
    AppTheme {
        ReviewScreen(
            reviews = listOf(),
            error = "",
            onErrorDismissed = {},
        )
    }
}
