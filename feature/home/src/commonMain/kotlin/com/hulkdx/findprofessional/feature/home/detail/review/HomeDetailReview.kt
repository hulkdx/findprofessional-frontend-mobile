@file:Suppress("FunctionName")

package com.hulkdx.findprofessional.feature.home.detail.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.commonui.CUAsyncImage
import com.hulkdx.findprofessional.core.commonui.CUTextButton
import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.core.model.pro.ProfessionalReview
import com.hulkdx.findprofessional.core.model.user.User
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.ic_star2
import com.hulkdx.findprofessional.core.resources.reviews
import com.hulkdx.findprofessional.core.resources.showAllReviews
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body1
import com.hulkdx.findprofessional.core.theme.body1Medium
import com.hulkdx.findprofessional.core.theme.body3
import kotlinx.datetime.Clock
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal fun LazyListScope.Review(
    professional: Professional,
    onShowMoreClick: () -> Unit,
) {
    if (professional.reviews.isEmpty()) return

    item { ReviewHeader(professional.reviewSize) }
    items(professional.reviews, key = { it.id }) { ReviewContent(it) }
    item { ShowMoreButton(onShowMoreClick) }
}

@Composable
private fun ReviewHeader(reviewSize: String) {
    Row(Modifier.padding(start = 16.dp, bottom = 16.dp)) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            style = body1Medium,
            text = "$reviewSize ${stringResource(Res.string.reviews)}",
        )
    }
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

@Composable
private fun ShowMoreButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = TopCenter,
    ) {
        CUTextButton(
            modifier = Modifier
                .testTag("showAllReviews")
                .padding(top = 16.dp, bottom = 30.dp)
                .padding(horizontal = 16.dp),
            text = stringResource(Res.string.showAllReviews),
            onClick = onClick,
        )
    }
}

@Preview
@Composable
private fun ReviewHeaderPreview() {
    AppTheme {
        Box(Modifier.background(Color.White)) {
            ReviewHeader("200")
        }
    }
}

@Preview
@Composable
private fun ReviewContentPreview() {
    AppTheme {
        ReviewContent(
            ProfessionalReview(
                id = 0,
                user = User(
                    profileImage = "",
                    firstName = "Stefan",
                    lastName = "Holman",
                    email = "",
                ),
                rate = 4,
                contentText = "Authentic and Wonderful 12-days tour of Paris. 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris.\nfeeling like I’ve learned a lot.",
                createdAt = Clock.System.now(),
                updatedAt = Clock.System.now(),
            )
        )
    }
}

@Preview
@Composable
private fun ReviewContentNoProfileImagePreview() {
    AppTheme {
        ReviewContent(
            ProfessionalReview(
                id = 0,
                user = User(
                    profileImage = null,
                    firstName = "Stefan",
                    lastName = "Holman",
                    email = "",
                ),
                rate = 4,
                contentText = "Authentic and Wonderful 12-days tour of Paris. 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris. Authentic and Wonderful 12-days tour of Paris.\nfeeling like I’ve learned a lot.",
                createdAt = Clock.System.now(),
                updatedAt = Clock.System.now(),
            )
        )
    }
}

@Preview
@Composable
private fun ShowMoreButtonPreview() {
    AppTheme {
        ShowMoreButton {}
    }
}
