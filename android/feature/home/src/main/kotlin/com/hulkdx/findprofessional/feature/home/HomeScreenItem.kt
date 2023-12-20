package com.hulkdx.findprofessional.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.core.commonui.CUAsyncImage
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body2
import com.hulkdx.findprofessional.core.theme.h1
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
            .padding(24.dp),
    ) {
        TopRow(professional)
        Description(professional)
        BottomRow(professional, onLikeClick)
    }
}

@Composable
private fun TopRow(professional: Professional) {
    Row {
        ProfileImage(professional)
        Column(
            Modifier
                .padding(start = 8.dp)
                .align(CenterVertically)
                .weight(1F)
        ) {
            FullName(professional)
            CoachType(professional)
        }
        Rating(professional)
    }
}

@Composable
private fun ProfileImage(professional: Professional) {
    CUAsyncImage(
        modifier = Modifier
            .size(50.dp)
            .clip(shape = CircleShape),
        url = professional.profileImageUrl ?: "",
    )
}

@Composable
private fun FullName(professional: Professional) {
    Text(
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
private fun RowScope.Rating(professional: Professional) {
    if (professional.rating == null) {
        return
    }
    Row(
        modifier = Modifier.align(CenterVertically)
            .padding(end = 8.dp),
    ) {
        Icon(
            modifier = Modifier
                .size(16.dp)
                .align(CenterVertically),
            painter = painterResource(R.drawable.ic_star),
            tint = MaterialTheme.colorScheme.scrim,
            contentDescription = "",
        )
        Text(
            modifier = Modifier.padding(start = 2.dp),
            color = Color(0xFF9D9CAC),
            style = body2,
            maxLines = 1,
            text = professional.rating ?: "0.0",
        )
    }
}

@Composable
fun BottomRow(
    professional: Professional,
    onLikeClick: (Professional) -> Unit,
) {
    Row {
        Price(professional)
        Spacer(modifier = Modifier.weight(1F))
        LikeButton(professional, onLikeClick)
    }
}

@Composable
private fun RowScope.LikeButton(
    professional: Professional,
    onLikeClick: (Professional) -> Unit,
) {
    IconButton(
        modifier = Modifier
            .padding(top = 8.dp)
            .align(CenterVertically),
        onClick = singleClick { onLikeClick(professional) },
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.ic_like),
            contentDescription = "",
            colorFilter = ColorFilter.tint(
                if (professional.isFav) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.onError
            )
        )
    }
}

@Composable
private fun Description(professional: Professional) {
    val description = professional.description ?: return
    Text(
        modifier = Modifier
            .padding(
                top = 16.dp,
            ),
        style = body2,
        text = description,
    )
}

@Composable
private fun Price(professional: Professional) {
    Row(Modifier.padding(top = 16.dp)) {
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
            color = Color(0xFF9D9CAC),
        )
        Text(
            modifier = Modifier
                .padding(bottom = 4.dp)
                .align(Bottom),
            style = body2,
            text = stringResource(MR.strings.perHour.resourceId),
            maxLines = 1,
            color = Color(0xFF9D9CAC),
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
