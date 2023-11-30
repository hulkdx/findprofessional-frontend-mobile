package com.hulkdx.findprofessional.feature.home.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.common.feature.authentication.model.User
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalReview
import com.hulkdx.findprofessional.common.feature.home.utils.Availability
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.core.commonui.CUAsyncImage
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.core.theme.body1
import com.hulkdx.findprofessional.core.theme.body1Medium
import com.hulkdx.findprofessional.core.theme.body2
import com.hulkdx.findprofessional.core.theme.h3
import com.hulkdx.findprofessional.resources.MR
import kotlinx.datetime.Clock
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeDetailScreen(
    viewModel: HomeDetailViewModel = getViewModel(),
) {
    val professional by viewModel.professional.collectAsStateWithLifecycle()
    val availability by viewModel.availability.collectAsStateWithLifecycle()

    HomeDetailScreen(
        professional,
        availability,
        viewModel::onReviewShowMoreClicked,
        viewModel::onBookClick
    )
}

@Composable
fun HomeDetailScreen(
    professional: Professional,
    availability: Availability?,
    onReviewShowMoreClicked: () -> Unit,
    onBookClicked: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.onPrimary)
            .testTag("HomeDetailScreen")
    ) {
        item { TopHeader(professional) }
        // TODO: get the timezone from the user
        Availability(availability, "UTC +03.00")
        Review(professional, onReviewShowMoreClicked)
    }
}

@Composable
private fun TopHeader(professional: Professional) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
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

@Preview(heightDp = 1500)
@Composable
private fun HomeDetailScreenPreview() {
    AppTheme {
        HomeDetailScreen(
            Professional(
                1,
                "test@email.com",
                "Luba",
                "Mikaela",
                "Life coach",
                100,
                "EUR",
                "https://i.imgur.com/5Yma8Kl.jpeg",
                "5.0",
                "former professional boxer who competed from 1985 to 2005",
                availability = listOf(),
                reviews = listOf(
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
                ),
            ),
            availability = listOf(
                listOf("", "Thu\n19", "Fri\n20", "Sat\n21", "Sun\n22", "Mon\n23", "Tue\n24"),
                listOf("00-04", "0", "0", "0", "0", "0", "0"),
                listOf("04-08", "0", "1", "0", "0", "0", "0"),
                listOf("08-12", "0", "0", "2", "0", "0", "0"),
                listOf("12-16", "0", "0", "0", "3", "0", "0"),
                listOf("16-20", "0", "0", "0", "0", "0", "0"),
                listOf("20-24", "0", "0", "0", "0", "4", "0"),
            ),
            {},
            {},
        )
    }
}
