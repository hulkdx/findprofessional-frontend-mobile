@file:Suppress("FunctionName")

package com.hulkdx.findprofessional.feature.home.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hulkdx.findprofessional.common.feature.authentication.model.User
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalReview
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.core.commonui.CUAsyncImage
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body3
import com.hulkdx.findprofessional.core.theme.body3Bold
import com.hulkdx.findprofessional.core.theme.body3SemiBold
import com.hulkdx.findprofessional.core.theme.h3Bold
import com.hulkdx.findprofessional.resources.MR
import kotlinx.datetime.Clock


internal fun LazyListScope.Review(
    professional: Professional,
    onShowMoreClicked: () -> Unit,
) {
    if (professional.reviews.isEmpty()) return

    item { ReviewHeader(professional.reviewSize) }
    items(professional.reviews) {
        ReviewContent(it)
    }
    item { ShowMoreButton(onShowMoreClicked) }
}

@Composable
private fun ReviewHeader(reviewSize: String) {
    Row(Modifier.padding(start = 16.dp, top = 32.dp, bottom = 16.dp)) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            style = h3Bold,
            text = "$reviewSize ${stringResource(MR.strings.reviews.resourceId)}",
        )
    }
}

@Composable
private fun ReviewContent(reviewContent: ProfessionalReview) {
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
    profileImageUrl ?: return
    CUAsyncImage(
        modifier = Modifier
            .size(35.dp)
            .clip(shape = CircleShape),
        url = profileImageUrl
    )
}

@Composable
private fun ReviewName(fullName: String) {
    Text(
        modifier = Modifier,
        text = fullName,
        color = Color(0xFF5F5F70),
        style = body3Bold,
    )
}

@Composable
private fun ReviewStar(star: Int) {
    Row(Modifier.padding(top = 2.dp)) {
        for (i in 1..star) {
            Image(
                modifier = Modifier,
                painter = painterResource(R.drawable.ic_star2),
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
        style = body3.copy(lineHeight = 21.sp),
        color = Color(0xFF717180),
    )
}

@Composable
private fun ReviewDate(date: String) {
    Text(
        modifier = Modifier
            .padding(top = 6.dp)
            .padding(horizontal = 16.dp),
        text = date,
        style = body3SemiBold,
        color = Color(0xFFAFAFBC),
    )
}

@Composable
private fun ShowMoreButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 64.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = Color.Black,
        ),
        shape = RoundedCornerShape(10.dp),
    ) {
        Text(
            "Show More",
            color = Color(0xFF717180),
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
private fun ReviewContentStarPreview() {
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
private fun ShowMoreButtonPreview() {
    AppTheme {
        ShowMoreButton {}
    }
}
