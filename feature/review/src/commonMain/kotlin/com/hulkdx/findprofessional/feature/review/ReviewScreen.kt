package com.hulkdx.findprofessional.feature.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.features.pro.Professional
import com.hulkdx.findprofessional.core.features.pro.ProfessionalReview
import com.hulkdx.findprofessional.core.features.user.User
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.ic_star2
import com.hulkdx.findprofessional.core.resources.reviews
import com.hulkdx.findprofessional.core.ui.commonui.CUAsyncImage
import com.hulkdx.findprofessional.core.ui.commonui.CUBackButton
import com.hulkdx.findprofessional.core.ui.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.ui.commonui.pagination.LazyColumnWithLastItem
import com.hulkdx.findprofessional.core.ui.theme.AppTheme
import com.hulkdx.findprofessional.core.ui.theme.body1
import com.hulkdx.findprofessional.core.ui.theme.body1Medium
import com.hulkdx.findprofessional.core.ui.theme.body3
import com.hulkdx.findprofessional.core.ui.theme.h1Medium
import kotlinx.datetime.Clock
import org.jetbrains.compose.resources.painterResource
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
        LazyColumnWithLastItem(
            modifier = Modifier.padding(top = 64.dp),
            onLastItemVisible = onLastItemVisible,
        ) {
            item { ReviewHeader(reviewSize) }
            items(reviews, key = { it.id }) { ReviewContent(it) }
        }
        CUBackButton(modifier = Modifier.align(Alignment.TopStart))
        CUSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            message = error,
            onDismiss = onErrorDismissed,
        )
    }
}

@Composable
fun ReviewHeader(reviewSize: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
        style = h1Medium,
        textAlign = TextAlign.Center,
        text = "$reviewSize ${stringResource(Res.string.reviews)}",
    )
}

@Composable
fun ReviewContent(reviewContent: ProfessionalReview) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(bottom = 16.dp)
    ) {
        Header(reviewContent)
        ReviewText(reviewContent.contentText)
        ReviewDate(reviewContent.formattedDate)
    }
}


@Composable
private fun Header(review: ProfessionalReview) {
    Row(Modifier.padding(top = 18.dp, start = 18.dp)) {
        ReviewProfile(review.user.profileImage)
        Column(
            Modifier
                .align(CenterVertically)
                .padding(start = 12.dp)
        ) {
            ReviewName(review.user.fullName)
            ReviewStar(review.rate)
        }
    }
}


@Composable
private fun ReviewProfile(profileImageUrl: String?) {
    CUAsyncImage(
        modifier = Modifier
            .size(50.dp)
            .clip(shape = CircleShape), url = profileImageUrl ?: ""
    )
}

@Composable
private fun ReviewName(fullName: String) {
    Text(
        modifier = Modifier,
        text = fullName,
        color = MaterialTheme.colorScheme.outline,
        style = body1Medium,
    )
}

@Composable
private fun ReviewStar(star: Int) {
    Row(Modifier.padding(top = 2.dp)) {
        for (i in 1..star) {
            Image(
                modifier = Modifier,
                painter = painterResource(Res.drawable.ic_star2),
                contentDescription = "",
            )
        }
    }
}


@Composable
private fun ReviewText(text: String?) {
    text ?: return
    Text(
        modifier = Modifier
            .padding(top = 12.dp)
            .padding(horizontal = 16.dp),
        text = text,
        style = body1,
        color = MaterialTheme.colorScheme.outline,
    )
}

@Composable
private fun ReviewDate(date: String) {
    Text(
        modifier = Modifier
            .padding(top = 18.dp)
            .padding(horizontal = 16.dp),
        text = date,
        style = body3,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
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
