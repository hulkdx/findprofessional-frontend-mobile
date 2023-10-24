package com.hulkdx.findprofessional.feature.home.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import com.hulkdx.findprofessional.core.theme.body2
import com.hulkdx.findprofessional.core.theme.h3
import com.hulkdx.findprofessional.resources.MR
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeDetailScreen(
    professional: Professional,
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onTertiary)
            .verticalScroll(rememberScrollState())
    ) {
        TopHeader(professional)
        Availability(professional)
    }
}

@Composable
private fun TopHeader(professional: Professional) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
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
private fun Availability(professional: Professional) {
    AvailabilityHeader()
    AvailabilityContent(professional)
}

@Composable
private fun AvailabilityHeader() {
    Row(Modifier.padding(start = 16.dp, top = 16.dp)) {
        Icon(
            painter = painterResource(R.drawable.ic_calendar_clock),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            style = h3,
            text = stringResource(MR.strings.availability.resourceId)
        )
    }
}

@Composable
private fun AvailabilityContent(professional: Professional) {
    // TODO:
}

@Composable
private fun Review(professional: Professional) {
    ReviewHeader()
    ReviewContent(professional)
}

@Composable
private fun ReviewHeader() {
    Row(Modifier.padding(start = 16.dp, top = 16.dp)) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            style = h3,
            text = stringResource(MR.strings.reviews.resourceId)
        )
    }
}

@Composable
private fun ReviewContent(professional: Professional) {
}

@Composable
private fun Video(professional: Professional) {
    VideoHeader()
    VideoContent(professional)
}

@Composable
private fun VideoHeader() {
    Row(Modifier.padding(start = 16.dp, top = 16.dp)) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            style = h3,
            text = stringResource(MR.strings.videos.resourceId)
        )
    }
}

@Composable
private fun VideoContent(professional: Professional) {
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
