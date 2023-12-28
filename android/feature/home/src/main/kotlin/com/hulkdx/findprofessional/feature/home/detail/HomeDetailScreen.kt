package com.hulkdx.findprofessional.feature.home.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.common.feature.authentication.model.User
import com.hulkdx.findprofessional.common.feature.home.detail.availability.AvailabilityData
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalReview
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.feature.home.Description
import com.hulkdx.findprofessional.feature.home.TopRow
import com.hulkdx.findprofessional.feature.home.detail.HomeScreenDimens.outerHorizontalPadding
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
        viewModel::onBookClick,
        viewModel::availabilityMonthMinusOne,
        viewModel::availabilityMonthPlusOne,
    )
}

@Composable
fun HomeDetailScreen(
    professional: Professional,
    availability: AvailabilityData,
    onReviewShowMoreClicked: () -> Unit,
    onBookClicked: () -> Unit,
    availabilityMonthMinusOne: () -> Unit,
    availabilityMonthPlusOne: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(top = 49.dp)
            .testTag("HomeDetailScreen")
    ) {
        item { TopHeader(professional) }
        Availability(availability, availabilityMonthMinusOne, availabilityMonthPlusOne)
        Review(professional, onReviewShowMoreClicked)
    }
}

@Composable
private fun TopHeader(professional: Professional) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = outerHorizontalPadding.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(24.dp)
    ) {
        TopRow(professional)
        Description(professional)
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
                reviewSize = "100",
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
            availability = AvailabilityData(
                "January 2022",
                5,
                31,
                1,
                listOf()
            ),
            {},
            {},
            {},
            {},
        )
    }
}
