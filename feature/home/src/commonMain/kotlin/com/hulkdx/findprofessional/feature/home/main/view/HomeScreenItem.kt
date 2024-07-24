package com.hulkdx.findprofessional.feature.home.main.view

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.core.commonui.CUAsyncImage
import com.hulkdx.findprofessional.core.commonui.icons.CULikeButton
import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.ic_star
import com.hulkdx.findprofessional.core.resources.perHour
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body2
import com.hulkdx.findprofessional.core.theme.h1SemiBold
import com.hulkdx.findprofessional.core.theme.h2Medium
import com.hulkdx.findprofessional.core.utils.CurrencyFormatter
import com.hulkdx.findprofessional.core.utils.singleClick
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

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
fun TopRow(professional: Professional) {
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
        color = MaterialTheme.colorScheme.outline,
        text = professional.fullName,
    )
}

@Composable
private fun CoachType(professional: Professional) {
    Text(
        style = body2,
        color = MaterialTheme.colorScheme.errorContainer,
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
        modifier = Modifier
            .align(CenterVertically)
            .padding(end = 8.dp),
    ) {
        Icon(
            modifier = Modifier
                .size(16.dp)
                .align(CenterVertically),
            painter = painterResource(Res.drawable.ic_star),
            tint = MaterialTheme.colorScheme.scrim,
            contentDescription = "",
        )
        Text(
            modifier = Modifier.padding(start = 4.dp),
            color = MaterialTheme.colorScheme.errorContainer,
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
    CULikeButton(
        modifier = Modifier
            .padding(top = 8.dp)
            .align(CenterVertically),
        isSelected = professional.isFav,
        onClick = singleClick { onLikeClick(professional) },
    )
}

@Composable
fun Description(professional: Professional) {
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
            style = h1SemiBold,
            maxLines = 1,
            text = professional.priceNumber.toString(),
        )
        Text(
            modifier = Modifier
                .padding(bottom = 4.dp)
                .align(Bottom),
            style = body2,
            maxLines = 1,
            text = " ${CurrencyFormatter.toSymbol(professional.priceCurrency)} ",
            color = MaterialTheme.colorScheme.errorContainer,
        )
        Text(
            modifier = Modifier
                .padding(bottom = 4.dp)
                .align(Bottom),
            style = body2,
            text = stringResource(Res.string.perHour),
            maxLines = 1,
            color = MaterialTheme.colorScheme.errorContainer,
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
