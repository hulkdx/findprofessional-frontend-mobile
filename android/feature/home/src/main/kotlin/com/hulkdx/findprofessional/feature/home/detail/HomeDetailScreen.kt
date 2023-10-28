package com.hulkdx.findprofessional.feature.home.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hulkdx.findprofessional.common.feature.home.Professional
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.core.commonui.CUAsyncImage
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body1
import com.hulkdx.findprofessional.core.theme.body1Medium
import com.hulkdx.findprofessional.core.theme.body2
import com.hulkdx.findprofessional.core.theme.body3
import com.hulkdx.findprofessional.core.theme.h3
import com.hulkdx.findprofessional.core.theme.h3Bold
import com.hulkdx.findprofessional.resources.MR
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeDetailScreen(
    viewModel: HomeDetailViewModel = getViewModel(),
) {
    HomeDetailScreen(
        viewModel.professional,
    )
}

@Composable
private fun HomeDetailScreen(
    professional: Professional,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onTertiary)
    ) {
        item { TopHeader(professional) }
        item { AvailabilityHeader() }
        item { AvailabilityContentTop() }
        items(professional.availabilities) {
            AvailabilityContentRow(it)
        }
        // TODO: get the timezone from user
        item { AvailabilityContentBottom("UTC +03.00") }
        item { Review(professional) }
    }
}

@Composable
private fun TopHeader(professional: Professional) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        ProfileImage(professional)
        FullName(professional)
        CoachType(professional)
        Description(professional)
        Price(professional)
        Rating(professional)
    }
}

@Composable
fun ColumnScope.ProfileImage(
    professional: Professional,
) {
    CUAsyncImage(
        modifier = Modifier
            .align(CenterHorizontally)
            .padding(top = 20.dp)
            .size(80.dp)
            .clip(shape = CircleShape),
        url = professional.profileImageUrl ?: ""
    )
}

@Composable
private fun ColumnScope.FullName(professional: Professional) {
    Text(
        modifier = Modifier
            .padding(top = 16.dp)
            .align(CenterHorizontally),
        style = h3,
        maxLines = 1,
        text = professional.fullName,
    )
}

@Composable
private fun ColumnScope.CoachType(professional: Professional) {
    Text(
        modifier = Modifier
            .padding(top = 4.dp)
            .align(CenterHorizontally),
        style = body1Medium,
        color = MaterialTheme.colorScheme.onTertiaryContainer,
        maxLines = 1,
        text = professional.coachType ?: "",
    )
}

@Composable
private fun Description(professional: Professional) {
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
    Row(
        Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    ) {
        Text(
            modifier = Modifier.weight(1F),
            style = body1,
            text = stringResource(MR.strings.hourly_rate.resourceId),
            maxLines = 1,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
        )
        Text(
            modifier = Modifier,
            style = body2,
            maxLines = 1,
            text = professional.price,
        )
    }
}

@Composable
private fun Rating(professional: Professional) {
    Row(
        Modifier
            .padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            modifier = Modifier.weight(1F),
            style = body1,
            text = stringResource(MR.strings.rating.resourceId),
            maxLines = 1,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
        )
        RatingIcon(professional)
        Text(
            text = "(1000)",
            color = MaterialTheme.colorScheme.scrim,
        )
    }
}

@Composable
private fun RatingIcon(professional: Professional) {
    if (professional.rating == null) {
        Icon(
            modifier = Modifier,
            painter = painterResource(R.drawable.ic_new_rating),
            tint = MaterialTheme.colorScheme.scrim,
            contentDescription = "",
        )
        return
    }
    Row(
        modifier = Modifier,
    ) {
        Icon(
            modifier = Modifier.align(Alignment.CenterVertically),
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
private fun AvailabilityHeader() {
    Header(
        modifier = Modifier.padding(top = 16.dp),
        text = stringResource(MR.strings.availability.resourceId)
    )
}

@Composable
private fun AvailabilityContentTop() {
    Spacer(
        Modifier
            .fillMaxWidth()
            .height(16.dp)
            .padding(start = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 16.dp))
            .background(MaterialTheme.colorScheme.onPrimary)
    )
}

@Composable
private fun AvailabilityContentBottom(timeZone: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 16.dp))
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(12.dp),
        text = "Base on your timezone (UTC +03:30)",
        style = body2,
        color = MaterialTheme.colorScheme.onTertiaryContainer,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun AvailabilityContentRow(rows: List<String>) {
    Row(
        Modifier
            .padding(start = 16.dp, end = 16.dp)
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(start = 12.dp, end = 25.dp)
    ) {
        for (item in rows) {
            AvailabilityContentElement(item)
        }
    }
}

@Composable
private fun RowScope.AvailabilityContentElement(row: String) {
    when (row) {
        "0" -> {
            AvailabilityBox(Color(0xFFF2F2F2))
        }

        "1" -> {
            AvailabilityBox(Color(0xFFE1F0D1))
        }

        "2" -> {
            AvailabilityBox(Color(0xFFC1DDA1))
        }

        "3" -> {
            AvailabilityBox(Color(0xFFA8D279))
        }

        "4" -> {
            AvailabilityBox(Color(0xFF8FC750))
        }

        else -> {
            AvailabilityText(row)
        }
    }
}

@Composable
private fun RowScope.AvailabilityBox(color: Color) {
    Box(
        Modifier
            .weight(1F)
            .fillMaxWidth()
            .height(30.dp)
            .padding(6.dp)
            .border(1.dp, Color(0xFF797979))
            .background(color)
    )
}

@Composable
private fun RowScope.AvailabilityText(row: String) {
    Text(
        text = row,
        modifier = Modifier.Companion
            .weight(1F)
            .align(Alignment.CenterVertically),
        style = body3,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun Review(professional: Professional) {
    ReviewHeader(professional)
    ReviewContent(professional)
}

@Composable
private fun ReviewHeader(professional: Professional) {
    Header(
        modifier = Modifier,
        text = professional.totalReviews + " " + stringResource(MR.strings.reviews.resourceId)
    )
}

@Composable
private fun ReviewContent(professional: Professional) {
}


@Composable
private fun Header(
    modifier: Modifier,
    text: String,
) {
    Row(modifier.padding(start = 16.dp, top = 32.dp, bottom = 16.dp)) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            style = h3Bold,
            text = text,
        )
    }
}

@Preview
@Composable
private fun HomeDetailScreenPreview() {
    AppTheme {
        HomeDetailScreen(
            Professional(
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
            )
        )
    }
}
