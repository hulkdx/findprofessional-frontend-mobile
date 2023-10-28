@file:Suppress("FunctionName")

package com.hulkdx.findprofessional.feature.home.detail

import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.common.feature.home.Professional
import com.hulkdx.findprofessional.common.feature.home.Review
import com.hulkdx.findprofessional.core.commonui.CUAsyncImage
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body3Bold
import com.hulkdx.findprofessional.resources.MR


internal fun LazyListScope.Review(professional: Professional) {
    item { ReviewHeader(professional) }
    items(professional.reviews) {
        ReviewContent(it)
    }
}

@Composable
private fun ReviewHeader(professional: Professional) {
    Header(
        modifier = Modifier,
        text = professional.totalReviews + " " + stringResource(MR.strings.reviews.resourceId)
    )
}

@Composable
private fun ReviewContent(review: Review) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        Row(Modifier.padding(top = 18.dp, start = 18.dp)) {
            ReviewProfile(review.profileImageUrl)
            Column(
                Modifier.align(CenterVertically)
            ) {
                ReviewName(review.fullName)
            }
        }
    }
}

@Composable
private fun ReviewName(fullName: String) {
    Text(
        modifier = Modifier.padding(start = 12.dp),
        text = fullName,
        color = Color(0xFF5F5F70),
        style = body3Bold,
    )
}

@Composable
private fun ReviewProfile(profileImageUrl: String) {
    CUAsyncImage(
        modifier = Modifier
            .size(35.dp)
            .clip(shape = CircleShape),
        url = profileImageUrl
    )
}

@Preview
@Composable
private fun ReviewContentPreview() {
    AppTheme {
        ReviewContent(
            Review(
                profileImageUrl = "",
                firstName = "Stefan",
                lastName = "Holman"
            )
        )
    }
}
