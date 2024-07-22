package com.hulkdx.findprofessional.feature.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.commonui.pagination.SetupOnLastItemVisible
import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.core.model.pro.ProfessionalReview
import com.hulkdx.findprofessional.core.model.user.User
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.reviews
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.h1Medium
import com.hulkdx.findprofessional.feature.home.detail.review.ReviewContent
import kotlinx.datetime.Clock
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ReviewScreen(
    professional: Professional,
    viewModel: ReviewViewModel = koinViewModel { parametersOf(professional) },
) {
    val error by viewModel.error.collectAsState()
    val reviews by viewModel.reviews.collectAsState()

    ReviewScreen(
        reviewSize = professional.reviewSize,
        reviews = reviews,
        onLastItemVisible = viewModel::onLastItemVisible,
        error = error?.localized(),
        onErrorDismissed = { viewModel.setError(null) },
    )
}

@Composable
fun ReviewScreen(
    reviewSize: String,
    reviews: List<ProfessionalReview>,
    onLastItemVisible: () -> Unit,
    error: String?,
    onErrorDismissed: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .systemBarsPadding()
            .testTag("ReviewScreen")
    ) {
        val state = rememberLazyListState()
        SetupOnLastItemVisible(state, onLastItemVisible)
        LazyColumn(state = state) {
            item { Header(reviewSize) }
            items(reviews, key = { it.id }) { ReviewContent(it) }
        }
        CUSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            message = error,
            onDismiss = onErrorDismissed,
        )
    }
}

@Composable
fun Header(reviewSize: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 45.dp,
                bottom = 32.dp,
            ),
        style = h1Medium,
        textAlign = TextAlign.Center,
        text = "$reviewSize ${stringResource(Res.string.reviews)}",
    )
}

@Preview
@Composable
private fun ReviewScreenPreview() {
    AppTheme {
        ReviewScreen(
            reviewSize = "150",
            reviews = listOf(
                ProfessionalReview(
                    id = 0,
                    user = User(
                        profileImage = null,
                        firstName = "Stefan",
                        lastName = "Holman",
                        email = "",
                    ),
                    rate = 5,
                    contentText = "He was a great coach for me!",
                    createdAt = Clock.System.now(),
                    updatedAt = Clock.System.now(),
                ),
                ProfessionalReview(
                    id = 1,
                    user = User(
                        profileImage = null,
                        firstName = "Bill",
                        lastName = "Gates",
                        email = "",
                    ),
                    rate = 1,
                    contentText = "I love it, but it was bad.",
                    createdAt = Clock.System.now(),
                    updatedAt = Clock.System.now(),
                )
            ),
            onLastItemVisible = {},
            error = "",
            onErrorDismissed = {},
        )
    }
}
