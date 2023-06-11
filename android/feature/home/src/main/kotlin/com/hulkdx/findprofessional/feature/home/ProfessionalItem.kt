package com.hulkdx.findprofessional.feature.home

import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.common.feature.home.Professional
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.core.commonui.CUAsyncImage
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.*
import com.hulkdx.findprofessional.core.theme.body1Medium
import com.hulkdx.findprofessional.core.theme.h3

@Composable
internal fun ProfessionalItem(
    professional: Professional,
    onLikeClick: (Professional) -> Unit,
    onItemClick: (Professional) -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(bottom = 20.dp)
        ,
    ) {
        Row {
            ProfileImage(professional)
            Column(
                Modifier
                    .padding(start = 16.dp)
                    .weight(1F)
            ) {
                Title(professional)
                CoachType(professional)
                Star(professional)
            }
            LikeButton(professional, onLikeClick)
        }
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
        url = professional.imageUrl
    )
}

@Composable
private fun Title(professional: Professional) {
    Text(
        modifier = Modifier.padding(top = 20.dp),
        style = h3,
        text = professional.title,
    )
}

@Composable
fun CoachType(professional: Professional) {
    Text(
        modifier = Modifier.padding(top = 8.dp),
        style = body1Medium,
        color = MaterialTheme.colorScheme.onTertiaryContainer,
        text = professional.type,
    )
}

@Composable
fun Star(professional: Professional) {
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
            modifier = Modifier.padding(start = 6.dp),
            color = MaterialTheme.colorScheme.scrim,
            text = professional.star,
        )
    }
}

@Composable
private fun LikeButton(
    professional: Professional,
    onLikeClick: (Professional) -> Unit,
) {
    IconButton(
        modifier = Modifier.padding(top = 6.dp),
        onClick = { onLikeClick(professional) },
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_like),
            contentDescription = "",
        )
    }
}

@Composable
private fun Price(professional: Professional) {
    Text(
        modifier = Modifier.padding(start = 16.dp, top = 24.dp,),
        style = body1,
        text = "Hourly rate",
        color = MaterialTheme.colorScheme.onTertiaryContainer,
    )
    Text(
        modifier = Modifier.padding(start = 16.dp, top = 4.dp),
        style = body2Bold,
        text = professional.currencyPrice + " " + professional.price,
    )
}

@Preview
@Composable
private fun ProfessionalItemPreview() {
    AppTheme {
        ProfessionalItem(
            professional = Professional(
                title = "Mike Tyson",
                type = "Life coach",
                price = "100",
                currencyPrice = "EUR",
                star = "5.0",
                imageUrl = "https://imgur.com/gallery/7R6wmYb"
            ),
            onLikeClick = {},
            onItemClick = {},
        )
    }
}
