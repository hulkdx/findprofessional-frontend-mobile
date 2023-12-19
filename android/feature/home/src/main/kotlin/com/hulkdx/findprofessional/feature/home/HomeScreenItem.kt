package com.hulkdx.findprofessional.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.core.commonui.CUAsyncImage
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body1
import com.hulkdx.findprofessional.core.theme.body2
import com.hulkdx.findprofessional.core.theme.body2Bold
import com.hulkdx.findprofessional.core.theme.body2Medium
import com.hulkdx.findprofessional.core.theme.body3
import com.hulkdx.findprofessional.core.theme.body3Medium
import com.hulkdx.findprofessional.core.theme.h1
import com.hulkdx.findprofessional.core.theme.h2
import com.hulkdx.findprofessional.core.theme.h2Medium
import com.hulkdx.findprofessional.core.utils.singleClick
import com.hulkdx.findprofessional.resources.MR

@Composable
fun HomeScreenItem(
    professional: Professional,
    onLikeClick: (Professional) -> Unit,
    onItemClick: (Professional) -> Unit,
) {
    Column(
        Modifier
            .testTag("pro-${professional.id}")
            .clickable(onClick = singleClick { onItemClick(professional) })
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(bottom = 16.dp)
        ,
    ) {
        TopRow(professional, onLikeClick)
        Description(professional)
        Price(professional)
    }
}

@Composable
private fun TopRow(
    professional: Professional,
    onLikeClick: (Professional) -> Unit,
) {
    Row {
        ProfileImage(professional)
        Column(
            Modifier
                .padding(start = 14.dp)
                .weight(1F)
        ) {
            FullName(professional)
            Row(Modifier.padding(top = 2.dp)) {
                CoachType(professional)
                Rating(professional)
            }
        }
        LikeButton(professional, onLikeClick)
    }
}

@Composable
private fun ProfileImage(professional: Professional) {
    val url = professional.profileImageUrl
    if (url.isNullOrBlank()) {
        return
    }
    CUAsyncImage(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp)
            .size(50.dp)
            .clip(shape = CircleShape),
        url = url,
    )
}

@Composable
private fun FullName(professional: Professional) {
    Text(
        modifier = Modifier.padding(top = 16.dp),
        style = h2Medium,
        maxLines = 1,
        color = Color.Black,
        text = professional.fullName,
    )
}

@Composable
private fun CoachType(professional: Professional) {
    Text(
        style = body2,
        color = Color(0xFF9D9CAC),
        maxLines = 1,
        text = professional.coachType ?: "",
    )
}

@Composable
private fun Rating(professional: Professional) {
    if (professional.rating == null) {
        return
    }
    Row(
        modifier = Modifier.padding(start = 10.dp),
    ) {
        Icon(
            modifier = Modifier
                .size(14.dp)
                .align(CenterVertically)
            ,
            painter = painterResource(R.drawable.ic_star),
            tint = MaterialTheme.colorScheme.scrim,
            contentDescription = "",
        )
        Text(
            modifier = Modifier.padding(start = 2.dp),
            color = MaterialTheme.colorScheme.scrim,
            style = body2,
            maxLines = 1,
            text = professional.rating ?: "0.0",
        )
    }
}

@Composable
private fun LikeButton(
    professional: Professional,
    onLikeClick: (Professional) -> Unit,
) {
    IconButton(
        modifier = Modifier.padding(top = 6.dp, end = 8.dp),
        onClick = singleClick { onLikeClick(professional) },
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.ic_like),
            contentDescription = "",
        )
    }
}

@Composable
private fun Description(professional: Professional) {
    val description = professional.description ?: return
    Text(
        modifier = Modifier
            .padding(
                top = 14.dp,
                start = 16.dp,
                end = 16.dp,
            ),
        style = body2,
        text = description,
    )
}

@Composable
private fun Price(professional: Professional) {
    Row(Modifier.padding(start = 16.dp)) {
        Text(
            style = h1,
            maxLines = 1,
            text = professional.priceNumber.toString(),
        )
        Text(
            modifier = Modifier
                .padding(bottom = 4.dp)
                .align(Bottom),
            style = body2,
            maxLines = 1,
            text = " ${professional.priceCurrencySymbol} ",
        )
        Text(
            modifier = Modifier
                .padding(bottom = 4.dp)
                .align(Bottom),
            style = body2,
            text = stringResource(MR.strings.perHour.resourceId),
            maxLines = 1,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
        )
    }
}

@Preview
@Composable
private fun ProfessionalItemPreview() {
    AppTheme {
        HomeScreenItem(
            professional = Professional(
                id = 1,
                firstName = "Mike",
                lastName = "Tyson",
                coachType = "Life coach",
                priceNumber = 100,
                priceCurrency = "EUR",
                email = "",
                profileImageUrl = "https://imgur.com/gallery/7R6wmYb",
                rating = "5.0",
                description = "former professional boxer who competed from 1985 to 2005",
                reviewSize = "100",
                availability = listOf(),
            ),
            onLikeClick = {},
            onItemClick = {},
        )
    }
}

@Preview
@Composable
private fun ProfessionalItemWithoutRatingPreview() {
    AppTheme {
        HomeScreenItem(
            professional = Professional(
                id = 1,
                firstName = "Mike",
                lastName = "Tyson",
                coachType = "Life coach",
                priceNumber = 100,
                priceCurrency = "EUR",
                email = "",
                profileImageUrl = "https://imgur.com/gallery/7R6wmYb",
                rating = null,
                description = "former professional boxer who competed from 1985 to 2005",
                reviewSize = "100",
                availability = listOf(),
            ),
            onLikeClick = {},
            onItemClick = {},
        )
    }
}
