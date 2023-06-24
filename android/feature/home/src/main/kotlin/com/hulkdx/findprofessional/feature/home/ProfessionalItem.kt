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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.common.feature.home.Professional
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.core.commonui.CUAsyncImage
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body1
import com.hulkdx.findprofessional.core.theme.body1Medium
import com.hulkdx.findprofessional.core.theme.body2Bold
import com.hulkdx.findprofessional.core.theme.h3
import com.hulkdx.findprofessional.core.utils.singleClick
import com.hulkdx.findprofessional.resources.MR

@Composable
internal fun ProfessionalItem(
    professional: Professional,
    onLikeClick: (Professional) -> Unit,
    onItemClick: (Professional) -> Unit,
) {
    Column(
        Modifier
            .clickable(onClick = singleClick { onItemClick(professional) })
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(bottom = 20.dp),
    ) {
        Row {
            ProfileImage(professional)
            Column(
                Modifier
                    .padding(start = 16.dp)
                    .weight(1F)
            ) {
                FullName(professional)
                CoachType(professional)
                Rating(professional)
            }
            LikeButton(professional, onLikeClick)
        }
        Description(professional)
        Price(professional)
    }
}

@Composable
private fun ProfileImage(professional: Professional) {
    CUAsyncImage(
        modifier = Modifier
            .padding(start = 16.dp, top = 24.dp)
            .size(52.dp)
            .clip(shape = CircleShape),
        url = professional.profileImageUrl ?: ""
    )
}

@Composable
private fun FullName(professional: Professional) {
    Text(
        modifier = Modifier.padding(top = 16.dp),
        style = h3,
        maxLines = 1,
        text = professional.fullName,
    )
}

@Composable
fun CoachType(professional: Professional) {
    Text(
        modifier = Modifier.padding(top = 4.dp),
        style = body1Medium,
        color = MaterialTheme.colorScheme.onTertiaryContainer,
        maxLines = 1,
        text = professional.coachType ?: "",
    )
}

@Composable
fun Rating(professional: Professional) {
    Row(
        modifier = Modifier.padding(top = 2.dp),
    ) {
        Icon(
            modifier = Modifier.align(CenterVertically),
            painter = painterResource(R.drawable.ic_star),
            tint = MaterialTheme.colorScheme.scrim,
            contentDescription = "",
        )
        Text(
            modifier = Modifier.padding(start = 2.dp),
            color = MaterialTheme.colorScheme.scrim,
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
fun Description(professional: Professional) {
    val description = professional.description ?: return
    Text(
        modifier = Modifier
            .padding(
                top = 12.dp,
                start = 16.dp,
                end = 16.dp,
            ),
        style = body1,
        text = description,
    )
}

@Composable
private fun Price(professional: Professional) {
    Text(
        modifier = Modifier
            .padding(
                top = 16.dp,
                start = 16.dp,
            ),
        style = body1,
        text = stringResource(MR.strings.hourly_rate.resourceId),
        maxLines = 1,
        color = MaterialTheme.colorScheme.onTertiaryContainer,
    )
    Text(
        modifier = Modifier.padding(start = 16.dp),
        style = body2Bold,
        maxLines = 1,
        text = professional.price,
    )
}

@Preview
@Composable
private fun ProfessionalItemPreview() {
    AppTheme {
        ProfessionalItem(
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
                description = "former professional boxer who competed from 1985 to 2005"
            ),
            onLikeClick = {},
            onItemClick = {},
        )
    }
}
